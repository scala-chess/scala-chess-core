package model.logic.modifier.conditionalMapper

import chess.api.{Action, Move, MoveAndReplace}
import model.History
import model.logic.modifier.ConditionalMapper

trait MoveReplaceMapper extends ConditionalMapper[Move, MoveAndReplace] {
  override def mapFunction: (((Int, Int), History, Move)) => Seq[MoveAndReplace] = Mapper.moveAndReplaceMapper

  override def filterFunction: (((Int, Int), History, Action)) => Boolean = Filter.targetOnTriggerField
}
