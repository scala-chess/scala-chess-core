package model.logic.modifier.conditionalMapper

import chess.api.{Action, _}
import model.History

object Filter {

  def targetOnTriggerField(t: (Position, History, Action)): Boolean =
    t._2.triggerPositions(t._3.pieceId).contains(t._3.target)

}
