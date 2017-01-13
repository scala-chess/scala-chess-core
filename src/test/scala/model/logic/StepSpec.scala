package model.logic

import chess.api._
import model.{History, TestBoard}
import org.specs2._
import model.TupleUtils._

class StepSpec extends Specification {
  def is =
    s2"""

  A Step should
    1. create actions for all directions and default step size 1      $actionsDefault
    2. create actions with given step size                            $actionsWithStep
    3. only return actions on the board                               $actionsOnBoard
                          """

  def directions = Seq(
    (t: Position) => t.up,
    (t: Position) => t.right
  )

  def actionsDefault = new Step(directions = directions).test must containTheSameElementsAs(
    Seq(
      (2, 1),
      (3, 2)
    )
  )

  def actionsWithStep = new Step(directions = directions, step = Some(2)).test must containTheSameElementsAs(
    Seq(
      (2, 0),
      (4, 2)
    )
  )

  def actionsOnBoard = new Step(directions = directions, step = Some(3)).test must containTheSameElementsAs(
    Seq(
      (5, 2)
    )
  )

  implicit class TestLogic(val logic: Logic) {
    def test = logic.getActions((2, 2), History(TestBoard())) map {
      _.target
    }
  }

}


