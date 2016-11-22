package model.logic.modifier

import chess.api.Action
import model.History
import model.logic.Logic

trait Unmoved extends Logic {
  override def getActions(field: (Int, Int), history: History): List[Action] =
    history.pieceAt(field) map {
      piece => history.unmoved(piece) match {
        case true => super.getActions(field, history)
        case false => List()
      }
    } getOrElse List()
}
