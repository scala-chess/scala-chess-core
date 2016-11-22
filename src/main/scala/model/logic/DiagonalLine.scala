package model.logic

import chess.api.{Action, Position}
import model.SeqExtensions._
import model.TupleUtils._
import model.actions.ActionFactory
import model.logic.modifier.IsOnBoard
import model.{History, Pattern}

class DiagonalLine(val maxSteps: Int) extends DiagonalLineMixin with IsOnBoard

trait DiagonalLineMixin extends Logic {
  val maxSteps: Int

  override def getActions(field: Position, history: History): Seq[Action] =
    history.pieceAt(field) map {
      piece =>
        Seq(
          (t: (Int, Int)) => t.up.right,
          (t: (Int, Int)) => t.up.left,
          (t: (Int, Int)) => t.down.right,
          (t: (Int, Int)) => t.down.left
        ) flatMap {
          dir => Pattern.line(dir, field, maxSteps) takeUntil (pos => history.pieceAt(pos).isDefined)
        } filter {
          target => history.pieceAt(target) forall {
            targetPiece => targetPiece.color != piece.color
          }
        } map {
          target => ActionFactory.move(piece.id, field, target, history)
        }
    } getOrElse Seq()
}

