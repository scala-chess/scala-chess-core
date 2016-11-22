package model.logic

import chess.api.{Action, Position}
import model.History

class Logic {
  def getActions(field: Position, history: History): List[Action] = List()
}

object Logic {
  def getActions(logic: List[Logic], field: Position, history: History): List[Action] =
    logic flatMap {
      l => l.getActions(field, history)
    }

}
