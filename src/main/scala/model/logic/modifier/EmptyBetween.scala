package model.logic.modifier

import chess.api._
import model.logic.Logic
import model.{History, Pattern}

trait EmptyBetween extends Logic {
  override def getActions(field: (Int, Int), history: History): Seq[Action] =
    super.getActions(field, history) filter {
      action => betweenX(action.origin.get, action.target) flatMap { pos => history.pieceAt(pos) } isEmpty
    }

  def betweenX(start: Position, end: Position): List[Position] = {
    val dir = Pattern.direction(start, end)
    start._1 match {
      case x if x == end._1 || dir(start)._1 == end._1 => List()
      case x => dir(start) :: betweenX(dir(start), end)
    }
  }
}
