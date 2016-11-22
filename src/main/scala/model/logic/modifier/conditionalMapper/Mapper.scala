package model.logic.modifier.conditionalMapper

import chess.api.{Move, MoveAndReplace, MoveAndReplaceChoice, Position}
import model._

object Mapper {

  def moveAndReplaceMapper(t: (Position, History, Move)): Seq[MoveAndReplace] = {
    t._2.pieceAt(t._1) map {
      piece =>
        Seq(
          (MoveAndReplaceChoice.Bishop, Bishop(piece.color)),
          (MoveAndReplaceChoice.Queen, Queen(piece.color)),
          (MoveAndReplaceChoice.Rook, Rook(piece.color)),
          (MoveAndReplaceChoice.Knight, Knight(piece.color))
        ) map {
          choiceAndPiece => ActionFactory.moveAndReplace(t._3, choiceAndPiece._1, choiceAndPiece._2)
        }
    } getOrElse Seq()
  }

}
