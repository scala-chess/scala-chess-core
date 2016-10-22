package model

trait Action {
  val origin: (Int, Int)
  val target: (Int, Int)
}
case class Move(origin: (Int, Int), target: (Int, Int)) extends Action
case class CompositeAction(origin: (Int, Int), target: (Int, Int), moves: Action*) extends Action

