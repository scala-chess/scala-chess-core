package model.logic

import chess.api.Action
import model.SeqExtensions._
import model.TupleUtils._
import model.actions.ActionFactory
import model.logic.modifier.IsOnBoard
import model.{History, Pattern}

class HorizontalVerticalLine(val maxSteps: Int) extends HorizontalVerticalLineMixin with IsOnBoard

trait HorizontalVerticalLineMixin extends Logic {
  val maxSteps: Int

  override def getActions(field: (Int, Int), history: History): Seq[Action] =
    history.pieceAt(field) map {
      piece =>
        Seq(
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
    } getOrElse Seq()
}


