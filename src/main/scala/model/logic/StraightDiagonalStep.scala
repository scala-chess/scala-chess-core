package model.logic

import chess.api.{Action, Position}
import model.TupleUtils._
import model.actions.ActionFactory
import model.logic.modifier.{EmptyBetween, IsOnBoard}
import model.{History, Pattern}

object StraightDiagonalStep {
  def apply(step: Int): StraightDiagonalStep = new StraightDiagonalStep(step) with EmptyBetween with IsOnBoard
}

class StraightDiagonalStep(step: Int) extends Logic {
  override def getActions(field: (Int, Int), history: History): List[Action] =
    history.pieceAt(field) map {
      piece => List(
        (t: Position) => t.straight(piece).left,
        (t: Position) => t.straight(piece).right
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
