package model.logic.modifier

import chess.api.Action
import model.History
import model.logic.Logic
import model.TupleUtils._

trait OnSameRow extends Logic {
  override def getActions(field: (Int, Int), history: History): Seq[Action] =
    super.getActions(field, history) filter {
      action => action.target.isSameRow(field)
    }
}
