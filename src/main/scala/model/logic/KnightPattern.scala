package model.logic

import chess.api.{Action, Position}
import model.TupleUtils._
import model.logic.modifier.IsOnBoard
import model.{ActionFactory, History}
import model.Pieces._

class KnightPattern extends KnightPatternMixin with IsOnBoard

trait KnightPatternMixin extends Logic {
  override def getActions(field: Position, history: History): Seq[Action] =
    history.pieceAt(field) map {
      piece =>
        Seq(
          field.right.right.up,
          field.right.right.down,
          field.left.left.up,
          field.left.left.down,
          field.up.up.left,
          field.up.up.right,
          field.down.down.left,
          field.down.down.right
        ) filter {
          target => history.pieceAt(target) forall {
            targetPiece => piece.isEnemy(targetPiece)
          }
        } map {
          target => ActionFactory.move(piece.id, field, target, history)
        }
    } getOrElse Seq()
}

