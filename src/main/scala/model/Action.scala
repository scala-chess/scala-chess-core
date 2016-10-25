package model

trait Action {
  val origin: (Int, Int)
  val target: (Int, Int)
}

trait ChoiceAction extends Action {

}
case class Move(origin: (Int, Int), target: (Int, Int)) extends Action
case class Castle(origin: (Int, Int), target: (Int, Int), targetKing: (Int,Int), targetRook: (Int,Int)) extends Action

//case class ChoiceAction(origin: (Int, Int), target: (Int, Int), action: Action, choice: Choice) extends Action
//case class ActionPostChoice(origin: (Int, Int), target: (Int, Int), action: Action, choice: Choice) extends Action