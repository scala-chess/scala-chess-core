package model.logic.move
import chess.api.Action
import model.History

class Castle extends Logic{
  override def getActions(field: (Int, Int), history: History): List[Action] = ???
}
