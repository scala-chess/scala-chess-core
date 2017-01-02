
import java.util.concurrent.TimeUnit

import akka.actor._
import akka.pattern.ask
import chess.api._
import model.Pieces._
import chess.api.actors.RegisterObserver

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.io.StdIn

class TUI(val chessController: ActorRef) extends Actor {

  chessController ! RegisterObserver
  implicit val timeout = akka.util.Timeout(5, TimeUnit.SECONDS)


  override def receive = {
    case u@Update(chessBoard, winner) => update(chessBoard.pieces)
  }

  def update(board: Iterable[(Position, Piece)]): Unit = {
    printBoard(board)
    var validActions: Seq[Action] = Seq()
    var selectedIndex = -1
    while (!validActions.toIndexedSeq.indices.contains(selectedIndex)) {
      validActions = Seq()
      while (validActions.isEmpty) {
        println("Select Unit:")
        print("x: ")
        val xStart = StdIn.readInt()
        print("y: ")
        val yStart = StdIn.readInt()
        val pos = (xStart, yStart)

        val result = chessController ? QueryValidActions(pos)

        validActions = Await.result(result, Duration.Inf) match {
          case actions: Seq[Action] => actions
        }

      }
      println("Choose Action:")
      validActions.zipWithIndex.foreach {
        // todo what to do here?
        //case (action: Choice, index) => println(index.toString + ": " + action.getClass.getSimpleName + " to " + action.target + " - " + action.choice.getClass.getSimpleName)
        case (action, index) => println(index.toString + ": " + action.getClass.getSimpleName + " to " + action.target)
      }
      selectedIndex = StdIn.readInt()
    }

    chessController ! validActions(selectedIndex)
  }

  def printBoard(board: Iterable[(Position, Piece)]) = {
    for (i <- 0 to 7) {
      for (j <- 0 to 7) {
        val letter = board find {
          t => t._1._1 == j && t._1._2 == i
        } map {
          t => t._2
        } match {
          case Some(piece) => piece.name match {
            case KING => inColor(piece, "K")
            case KNIGHT => inColor(piece, "k")
            case ROOK => inColor(piece, "R")
            case BISHOP => inColor(piece, "B")
            case QUEEN => inColor(piece, "Q")
            case PAWN => inColor(piece, "P")
            case _ =>
              println(piece.name)
              "?"
          }
          case None => "_"
        }
        print("|" + letter)
      }
      println("|")
    }
    println("")
  }

  def inColor(piece: Piece, letter: String): String =
    piece.color match {
      case Color.Black => Console.BLUE + letter + Console.BLACK
      case Color.White => Console.RED + letter + Console.BLACK
    }

}