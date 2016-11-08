
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

  def execIfValid(action: Action): Either[String, chess.api.ChessBoard] = {
    getValidActions(fold, action.origin).find(a => a == action).map {
      validAction: Action => {
        actions += validAction
        Right(toApiChessBoard)
      }
    } getOrElse Left("illegal action cannot be executed")
  }

  def toApiChessBoard: chess.api.ChessBoard = {
    val board = fold
    val dimX = board.matrix.length
    val dimY = board.matrix(0).length
    chess.api.ChessBoard(dimX, dimY, board.getAll)
  }

  def fold: Board = {
    val board = actions.foldLeft(initialBoard) {
      (board, action) =>
        board.get(action.origin) map {
          PieceLogic(_)
        } map {
          p => p.handle(board, action)
        } getOrElse board
    }
    board
  }

  def run() = {
    while (true) {
      val board = fold
      val newAction = tui.update(board)
      actions += newAction
    }
  }
}

