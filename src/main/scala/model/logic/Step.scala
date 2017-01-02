package model.logic

import chess.api.{Action, Direction, Position}
import model.TupleUtils._
import model.logic.modifier.{EmptyBetween, IsOnBoard}
import model.{ActionFactory, History, Pattern}
import model.Pieces._

class Step(val directions: Seq[Direction], val step: Option[Int] = None) extends StepMixin with EmptyBetween with IsOnBoard

trait StepMixin extends Logic {
  val step: Option[Int]
  val directions: Seq[Direction]

  override def getActions(field: Position, history: History): Seq[Action] =
    directions map {
        dir =>
          val stepUnwrapped = step getOrElse 1
          Pattern.line(dir, field, stepUnwrapped).last
      } map {
        target => InternalDefaultAction(target)
      }
}
