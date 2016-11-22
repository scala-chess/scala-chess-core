package model.logic.modifier

import chess.api.Action
import model.History
import model.logic.Logic

trait TargetEmpty extends Logic {
  override def getActions(field: (Int, Int), history: History): List[Action] =
    super.getActions(field, history) filter {
      action => history.pieceAt(action.target).isEmpty
    }
}
