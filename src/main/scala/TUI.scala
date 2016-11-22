
import chess.api._

import scala.io.StdIn

class TUI(game: Game) {

  def update(board: List[(Position, Piece)]): Action = {
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
        validActions = game.getValidActions((xStart, yStart))
      }
      println("Choose Action:")
      validActions.zipWithIndex.foreach {
        case (action: Choice, index) => println(index.toString + ": " + action.getClass.getSimpleName + " to " + action.target + " - " + action.choice.getClass.getSimpleName)
        case (action, index) => println(index.toString + ": " + action.getClass.getSimpleName + " to " + action.target)
      }
      selectedIndex = StdIn.readInt()
    }

    validActions.toIndexedSeq(selectedIndex)
  }

  def printBoard(board: List[(Position, Piece)]) = {
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