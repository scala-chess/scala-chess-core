package model.logic

import chess.api.{Action, Position}
import model.TupleUtils._
import model.actions.ActionFactory
import model.logic.modifier.{EmptyBetween, IsOnBoard}
import model.{History, Pattern}

object StraightStep {
  def apply(step: Int): StraightStep = new StraightStep(step) with EmptyBetween with IsOnBoard
}

class StraightStep(step: Int) extends Logic {
  override def getActions(field: (Int, Int), history: History): List[Action] =
    history.pieceAt(field) map {
      piece => List(
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
    } getOrElse List()
}
