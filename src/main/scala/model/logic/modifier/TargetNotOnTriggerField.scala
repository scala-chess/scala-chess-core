package model.logic.modifier

import chess.api.Action
import model.History
import model.logic.Logic

trait TargetNotOnTriggerField extends Logic {
  override def getActions(field: (Int, Int), history: History): Seq[Action] =
    super.getActions(field, history) filter {
      action => !history.triggerPositions(action.pieceId).contains(action.target)
    }
}
