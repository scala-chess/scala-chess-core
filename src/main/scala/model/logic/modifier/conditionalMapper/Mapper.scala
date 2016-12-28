package model.logic.modifier.conditionalMapper

import chess.api._
import model._
import model.Pieces._

object Mapper {

  def moveAndReplaceMapper(t: (Position, History, Move)): Seq[MoveAndReplace] = {
    t._2.pieceAt(t._1) map {
      piece =>
        Seq(
          (BISHOP, bishop(piece.color)),
          (QUEEN, queen(piece.color)),
          (ROOK, rook(piece.color)),
          (KNIGHT, knight(piece.color))
        ) map {
          choiceAndPiece => ActionFactory.moveAndReplace(t._3, choiceAndPiece._1, choiceAndPiece._2)
        }
    } getOrElse Seq()
  }

}
