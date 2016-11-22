package model.logic.modifier

import chess.api.Action
import model.History
import model.logic.Logic

trait IsOnBoard extends Logic {
  override def getActions(field: (Int, Int), history: History): List[Action] =
    super.getActions(field, history) filter {
      action => history.isOnBoard(action.target)
    }
}
