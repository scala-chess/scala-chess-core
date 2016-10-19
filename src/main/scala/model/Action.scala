package model

trait Action
trait Move
case class SimpleMove(from: (Int, Int), to: (Int, Int)) extends Move
case class CompositeMove(moves: Action*) extends Action