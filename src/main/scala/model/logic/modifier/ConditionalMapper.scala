package model.logic.modifier

import chess.api._
import model._
import model.logic.Logic

trait ConditionalMapper[T <: Action, U <: Action] extends Logic {
  def mapFunction: ((Position, History, T)) => Seq[U]

  def filterFunction: ((Position, History, Action)) => Boolean

  override def getActions(field: (Int, Int), history: History): Seq[Action] = {
    val partitioned = super.getActions(field, history) partition {
      case a: T if filterFunction((field, history, a)) => true
      case _ => false
    }

    val mapped = partitioned._1 collect {
      case a: T => a
    } map {
      (field, history, _)
    } flatMap mapFunction

    List.concat(mapped, partitioned._2)
  }
}






