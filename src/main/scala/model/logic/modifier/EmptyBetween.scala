package model.logic.modifier

import chess.api._
import model.logic.Logic
import model.{History, Pattern}

trait EmptyBetween extends Logic {
  override def getActions(field: (Int, Int), history: History): Seq[Action] =
    super.getActions(field, history) filter {
      action => between(field, action.target) flatMap { pos => history.pieceAt(pos) } isEmpty
    }

  def between(start: Position, end: Position): List[Position] = {
    val dir = Pattern.direction(start, end)
    start match {
      case x if x == end || dir(start) == end => List()
      case x => dir(start) :: between(dir(start), end)
    }
  }
}
