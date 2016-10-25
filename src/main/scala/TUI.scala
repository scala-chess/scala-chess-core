
import model.{Board, Color, Piece}
import model.pieces.{Bishop, King, Knight, Pawn, Queen, Rook}

import scala.io.StdIn

class TUI {

  def update(board: Board): ((Int, Int), (Int, Int)) = {
    printBoard(board)
    val xStart = StdIn.readInt()
    val yStart = StdIn.readInt()
    val xEnd = StdIn.readInt()
    val yEnd = StdIn.readInt()
    ((xStart, yStart), (xEnd, yEnd))

  }

  def printBoard(board: Board) = {
    var i = 0
    var j = 0

    for (i <- 0 to 7) {
      for (j <- 0 to 7) {
        val letter = board.get(j, i) match {
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