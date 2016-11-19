package model.logic.move

import chess.api.{Action, Position}
import model.History

trait Logic {
  def getActions(field: Position, history: History): List[Action]
}
