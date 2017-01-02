package model.logic

import chess.api.{Action, Atomic, Position}
import model.History

class Logic {
  def getActions(field: Position, history: History): Seq[Action] = Seq()
}

case class InternalDefaultAction(target: Position, pieceId: Int = -1) extends Action with Atomic

object Logic {
  def getActions(logic: Seq[Logic], field: Position, history: History): Seq[Action] =
    logic flatMap {
      l => l.getActions(field, history)
    }

}
