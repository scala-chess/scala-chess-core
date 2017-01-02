package model.logic

import chess.api.{Action, Direction, Piece, Position}
import model.TupleUtils._
import model.logic.modifier.EmptyBetween
import model.{ActionFactory, History, Pattern}
import model.Pieces._

class Castle extends CastleMixin with EmptyBetween

trait CastleMixin extends Logic {
  override def getActions(field: Position, history: History): Seq[Action] = {
    history.pieceAt(field) map {
      piece =>
        history.all flatMap {
          case (pos, rook@Piece(_, ROOK, id)) if piece.isAlly(rook) && history.unmoved(rook) && field.isSameRow(pos) => Some((pos, id))
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
  } getOrElse Seq()
}
