
import model.{Board}
import scala.io.StdIn
import chess.api._

class TUI(game: Game) {

  def update(board: Board): Action = {
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
        validActions = game.getValidActions(board, (xStart, yStart))
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