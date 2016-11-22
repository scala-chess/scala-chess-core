package model.logic

import chess.api.{Action, Position}
import model.TupleUtils._
import model.actions.ActionFactory
import model.logic.modifier.{EmptyBetween, IsOnBoard}
import model.{History, Pattern}

class StraightStep(val step: Int) extends StraightStepMixin with EmptyBetween with IsOnBoard

trait StraightStepMixin extends Logic {
  val step: Int

  override def getActions(field: (Int, Int), history: History): Seq[Action] =
    history.pieceAt(field) map {
      piece => Seq(
        (t: Position) => t.straight(piece)
      ) map {
        Pattern.line(_, field, step).last
      } filter {
        target => history.pieceAt(target) forall {
          targetPiece => targetPiece.color != piece.color
        }
      } map {
        target => ActionFactory.move(piece.id, field, target, history)
      }
    } getOrElse Seq()
}
