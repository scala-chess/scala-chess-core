package model.logic

import chess.api.Action
import model.History
import model.TupleUtils._
import model.actions.ActionFactory
import model.logic.modifier.IsOnBoard

object KnightPattern {
  def apply: KnightPattern = new KnightPattern() with IsOnBoard
}

class KnightPattern extends Logic {
  override def getActions(field: (Int, Int), history: History): List[Action] =
    history.pieceAt(field) map {
      piece =>
        List(
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
            targetPiece => targetPiece.color != piece.color
          }
        } map {
          target => ActionFactory.move(piece.id, field, target, history)
        }
    } getOrElse List()
}
