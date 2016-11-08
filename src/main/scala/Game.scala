
import chess.api._
import model.{Board, _}

import scala.collection.mutable

class Game {
  val tui = new TUI(this)
  val actions: mutable.MutableList[Action] = new mutable.MutableList[Action]
  val initialBoard = ChessBoard()

  def getValidActions(board: Board, origin: (Int, Int)): Iterable[Action] = {
    board.get(origin) map {
      PieceLogic(_).getActions(origin, board, actions)
    } getOrElse Iterable()
  }

  def run() = {
    while (true) {
      val board = actions.foldLeft(initialBoard) {
        (board, action) =>
          board.get(action.origin) map {
            PieceLogic(_)
          } map {
            p => p.handle(board, action)
          } getOrElse board
      }
      val newAction = tui.update(board)
      actions += newAction
    }
  }
}

