
import java.util.concurrent.TimeUnit

import akka.actor._
import akka.pattern.ask
import chess.api._
import chess.api.actors.RegisterObserver
import model.config.Pieces

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.util.Try

class TUI(val chessController: ActorRef) extends Actor {

  chessController ! RegisterObserver
  implicit val timeout = akka.util.Timeout(5, TimeUnit.SECONDS)

  trait Selection

  case class WaitForX() extends Selection

  case class WaitForY(x: Int) extends Selection

  case class WaitForChoice(x: Int, y: Int, validActinos: Seq[Action]) extends Selection

  var state: Selection = changeState(WaitForX())

  override def receive = {
    case u@Update(chessBoard, winner) =>
      printBoard(chessBoard.pieces)
      state = changeState(WaitForX())

    case input: Int => state match {
      case WaitForX() =>
        state = changeState(WaitForY(input))
      case WaitForY(x) =>
        val selectedPos = (x, input)
        val result = chessController ? QueryValidActions(selectedPos)
        val validActions = Await.result(result, Duration.Inf) match {
          case actions: Seq[Action] => actions
        }
        validActions match {
          case Seq() => state = changeState(WaitForX())
          case actions: Seq[Action] =>
            state = changeState(WaitForChoice(x, input, validActions))
            validActions.zipWithIndex.foreach {
              case (action: Choice, index) => println(index.toString + ": " + action.getClass.getSimpleName + " to " + action.target + " - " + action.choice)
              case (action, index) => println(index.toString + ": " + action.getClass.getSimpleName + " to " + action.target)
            }
        }
      case WaitForChoice(x, y, validActions) =>
        Try(validActions(input)).map(chessController ! _)
        state = changeState(WaitForX())
    }
  }

  def changeState(selection: Selection) = {
    selection match {
      case WaitForX() =>
        println("Select Unit:")
        print("x: ")
      case WaitForY(_) =>
        print("y: ")
      case WaitForChoice(_, _, _) =>
        println("Choose Action:")
    }
    selection
  }


  def printBoard(board: Iterable[(Position, Piece)]) = {
    println
    for (i <- 0 to 7) {
      for (j <- 0 to 7) {

        val letter = board collectFirst {
          case ((`j`, `i`), piece) => piece
        } match {
          case Some(piece) => inColor(piece)
          case None => "_"
        }
        print("|" + letter)
      }
      println("|")
    }
    println("")
  }

  def inColor(piece: Piece): String =
    piece.color match {
      case Color.Black => Console.BLUE + Pieces.getByName(piece.name).id + Console.BLACK
      case Color.White => Console.RED + Pieces.getByName(piece.name).id + Console.BLACK
    }

}