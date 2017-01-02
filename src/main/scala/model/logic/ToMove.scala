package model.logic

import chess.api.{Action, Direction, Piece, Position}
import model.TupleUtils._
import model.logic.modifier.EmptyBetween
import model.{ActionFactory, History, Pattern}
import model.Pieces._


trait ToMove extends Logic {
  override def getActions(field: Position, history: History): Seq[Action] = {
    history.pieceAt(field) map {
      piece =>
        super.getActions(field, history) map {
          action => ActionFactory.move(piece.id, field, action.target, history)
        }
    }
  } getOrElse Seq()
}
