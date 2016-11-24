package model.logic

import chess.api.{Action, Position}
import model.TupleUtils._
import model.logic.modifier.{EmptyBetween, IsOnBoard}
import model.{ActionFactory, History, Pattern}
import model.Pieces._

class StraightDiagonalStep(val step: Option[Int] = None) extends StraightDiagonalStepMixin with EmptyBetween with IsOnBoard

trait StraightDiagonalStepMixin extends Logic {
  val step: Option[Int]

  override def getActions(field: Position, history: History): Seq[Action] =
    history.pieceAt(field) map {
      piece => Seq(
        (t: Position) => t.straight(piece).left,
        (t: Position) => t.straight(piece).right
      ) map {
        dir =>
          val stepUnwrapped = step getOrElse 1
          Pattern.line(dir, field, stepUnwrapped).last
      } filter {
        target => history.pieceAt(target) forall {
          targetPiece => piece.isEnemy(targetPiece)
        }
      } map {
        target => ActionFactory.move(piece.id, field, target, history)
      }
    } getOrElse Seq()
}
