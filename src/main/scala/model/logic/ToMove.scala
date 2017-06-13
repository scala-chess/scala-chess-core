package model.logic

import chess.api.{Action, Position}
import model.{ActionFactory, History}


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
