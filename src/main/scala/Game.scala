
import chess.api._
import model.{Board, _}

import scala.collection.mutable

class Game {
  type AMove = Either[(Piece, (Int, Int)), ((Int, Int), (Int, Int))]
  val tui = new TUI(this)
  val actions: mutable.MutableList[Action] = new mutable.MutableList[Action]
  val initialBoard = new Board().set((4, 0), Some(model.pieces.King(Color.Black)))
    .set((4, 7), Some(model.pieces.King(Color.White)))
    .set((1, 0), Some(model.pieces.Knight(Color.Black)))
    .set((6, 0), Some(model.pieces.Knight(Color.Black)))
    .set((1, 7), Some(model.pieces.Knight(Color.White)))
    .set((6, 7), Some(model.pieces.Knight(Color.White)))
    .set((0, 0), Some(model.pieces.Rook(Color.Black)))
    .set((7, 0), Some(model.pieces.Rook(Color.Black)))
    .set((0, 7), Some(model.pieces.Rook(Color.White)))
    .set((7, 7), Some(model.pieces.Rook(Color.White)))
    .set((2, 0), Some(model.pieces.Bishop(Color.Black)))
    .set((5, 0), Some(model.pieces.Bishop(Color.Black)))
    .set((2, 7), Some(model.pieces.Bishop(Color.White)))
    .set((5, 7), Some(model.pieces.Bishop(Color.White)))
    .set((3, 0), Some(model.pieces.Queen(Color.Black)))
    .set((3, 7), Some(model.pieces.Queen(Color.White)))
    .set((0, 1), Some(model.pieces.Pawn(Color.Black)))
    .set((1, 1), Some(model.pieces.Pawn(Color.Black)))
    .set((2, 1), Some(model.pieces.Pawn(Color.Black)))
    .set((3, 1), Some(model.pieces.Pawn(Color.Black)))
    .set((4, 1), Some(model.pieces.Pawn(Color.Black)))
    .set((5, 1), Some(model.pieces.Pawn(Color.Black)))
    .set((6, 1), Some(model.pieces.Pawn(Color.Black)))
    .set((7, 1), Some(model.pieces.Pawn(Color.Black)))
    .set((0, 6), Some(model.pieces.Pawn(Color.White)))
    .set((1, 6), Some(model.pieces.Pawn(Color.Black)))
    .set((2, 6), Some(model.pieces.Pawn(Color.White)))
    .set((3, 6), Some(model.pieces.Pawn(Color.White)))
    .set((4, 6), Some(model.pieces.Pawn(Color.White)))
    .set((5, 6), Some(model.pieces.Pawn(Color.White)))
    .set((6, 6), Some(model.pieces.Pawn(Color.White)))
    .set((7, 6), Some(model.pieces.Pawn(Color.White)))

  def getValidActions(board: Board, origin: (Int, Int)): Iterable[Action] = {
    board.get(origin) map {
      PieceLogic(_).getActions(origin, board, actions) filter {
        a => board.isOnBoard(a.target)
      }
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

