package model.logic

import chess.api.{Action, Direction, Position}
import model.SeqExtensions._
import model.logic.modifier.IsOnBoard
import model.{ActionFactory, History, Pattern}

class Line(val directions: Seq[Direction], val maxSteps: Option[Int] = None, val stopOnCollision: Boolean = true) extends LineMixin with IsOnBoard

trait LineMixin extends Logic {
  val maxSteps: Option[Int]
  val directions: Seq[Direction]
  val stopOnCollision: Boolean

  override def getActions(field: Position, history: History): Seq[Action] =
    directions flatMap {
      dir =>
        val maxStepsUnwrapped = maxSteps getOrElse history.maxBoardSize
        val line = Pattern.line(dir, field, maxStepsUnwrapped)
        if (stopOnCollision) {
          line takeUntil (pos => history.pieceAt(pos).isDefined)
        } else {
          line
        }
    } map {
      target => InternalDefaultAction(target)
    }
}

