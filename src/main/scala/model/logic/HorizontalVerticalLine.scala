package model.logic

import chess.api.Action
import model.ListExtensions._
import model.TupleUtils._
import model.actions.ActionFactory
import model.logic.modifier.IsOnBoard
import model.{History, Pattern}

object HorizontalVerticalLine {
  def apply(maxSteps: Int): HorizontalVerticalLine = new HorizontalVerticalLine(maxSteps) with IsOnBoard
}

class HorizontalVerticalLine(val maxSteps: Int) extends Logic {
  override def getActions(field: (Int, Int), history: History): List[Action] =
    history.pieceAt(field) map {
      piece =>
        List(
          (t: (Int, Int)) => t.up,
          (t: (Int, Int)) => t.left,
          (t: (Int, Int)) => t.right,
          (t: (Int, Int)) => t.down
        ) flatMap {
          dir => Pattern.line(dir, field, 8) takeUntil { pos => history.pieceAt(pos).isDefined }
        } filter {
          target =>
            history.pieceAt(target) forall {
              targetPiece => targetPiece.color != piece.color
            }
        } map {
          target => ActionFactory.move(piece.id, field, target, history)
        }
    } getOrElse List()
}
