package model.logic

import chess.api.{Action, Position}
import model.History

class Logic {
  def getActions(field: Position, history: History): Seq[Action] = Seq()
}

object Logic {
  def getActions(logic: Seq[Logic], field: Position, history: History): Seq[Action] =
    logic flatMap {
      l => l.getActions(field, history)
    }

}
