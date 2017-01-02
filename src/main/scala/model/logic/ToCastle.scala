package model.logic

import chess.api.{Action, Direction, Piece, Position}
import model.TupleUtils._
import model.logic.modifier.EmptyBetween
import model.{ActionFactory, History, Pattern}
import model.Pieces._

trait ToCastle extends Logic {
  override def getActions(field: Position, history: History): Seq[Action] = {
    history.pieceAt(field) map {
      piece =>
        super.getActions(field, history) flatMap {
          action =>
            history.pieceAt(action.target) map {
              (action.target, _, Pattern.direction(field, action.target))
            }
        } map {
          case (rookPos, rook, dir) => ActionFactory.castle(piece.id, field, dir(dir(field)), rook.id, rookPos, dir(field), history)
        }
    }
  } getOrElse Seq()
}
