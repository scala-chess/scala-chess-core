
import java.util.concurrent.TimeUnit

import akka.actor._
import akka.pattern.ask
import chess.api._
import chess.api.actors.RegisterObserver

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.io.StdIn

class TUI(val chessController: ActorRef) extends Actor {

  chessController ! RegisterObserver
  implicit val timeout = akka.util.Timeout(5, TimeUnit.SECONDS)

  override def receive = {
    case u@Update(chessBoard) => update(chessBoard.pieces)
  }

  def update(board: Iterable[(Position, Piece)]): Unit = {
    printBoard(board)
    var validActions: Iterable[Action] = Iterable()
    var selectedIndex = -1
    while (!validActions.toIndexedSeq.indices.contains(selectedIndex)) {
      validActions = Iterable()
      while (validActions.isEmpty) {
        println("Select Unit:")
        print("x: ")
        val xStart = StdIn.readInt()
        print("y: ")
        val yStart = StdIn.readInt()
        val pos = (xStart, yStart)

        val result = chessController ? QueryValidActions(pos)
        Await.result(result, Duration.Inf) match {
          case actions: Iterable[Action] => validActions = actions
        }
        println(validActions)
        //validActions = game.getValidActions((xStart, yStart))
      }
      println("Choose Action:")
      validActions.zipWithIndex.foreach {
        case (action: Choice, index) => println(index.toString + ": " + action.getClass.getSimpleName + " to " + action.target + " - " + action.choice.getClass.getSimpleName)
        case (action, index) => println(index.toString + ": " + action.getClass.getSimpleName + " to " + action.target)
      }
      selectedIndex = StdIn.readInt()
    }
    chessController ! validActions.toIndexedSeq(selectedIndex)
  }

  def printBoard(board: Iterable[(Position, Piece)]) = {
    var i = 0
    var j = 0

    for (i <- 0 to 7) {
      for (j <- 0 to 7) {
        val letter = board find {
          t => t._1._1 == j && t._1._2 == i
        } map {
          t => t._2
        } match {
          case None => "_"
          case Some(piece) => piece match {
            case k: King => inColor(k, "K")
            case k: Knight => inColor(k, "k")
            case r: Rook => inColor(r, "R")
            case b: Bishop => inColor(b, "B")
            case q: Queen => inColor(q, "Q")
            case p: Pawn => inColor(p, "P")
          }
        }
        print("|" + letter)
      }
      println("")
    }

    println("")
  }

  def inColor(piece: Piece, letter: String): String =
    piece.color match {
      case Color.Black => Console.BLUE + letter + Console.BLACK
      case Color.White => Console.RED + letter + Console.BLACK
    }

}