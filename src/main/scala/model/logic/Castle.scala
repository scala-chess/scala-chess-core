package model.logic

import chess.api.{Action, Direction, Position}
import model.TupleUtils._
import model.actions.ActionFactory
import model.logic.modifier.EmptyBetween
import model.{History, Pattern}

object Castle {
  def apply(): Castle = new Castle() with EmptyBetween
}

class Castle extends Logic {
  override def getActions(field: Position, history: History): List[Action] = {
    history.pieceAt(field) map {
      piece =>
        history.all flatMap {
          case ((x, y), rook@chess.api.Rook(color, id)) if color == piece.color && history.unmoved(rook) && y == field.y => Some(((x, y), id))
          case _ => None
        } map {
          rookPosId => (rookPosId, Pattern.direction(field, rookPosId._1))
        } map {
          t =>
            val rookPosId: (Position, Int) = t._1
            val dir: Direction = t._2
            ActionFactory.castle(piece.id, field, dir(dir(field)), rookPosId._2, rookPosId._1, dir(field), history)
        }
    }
  } getOrElse List()
}
