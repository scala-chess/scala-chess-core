package model

import chess.api.Action

object ListExtensions {

  implicit class TakeUntilListWrapper[T](list: List[T]) {
    def takeUntil(predicate: T => Boolean): List[T] = {
      def rec(tail: List[T], accum: List[T]): List[T] = tail match {
        case Nil => accum.reverse
        case h :: t => rec(if (predicate(h)) Nil else t, h :: accum)
      }
      rec(list, Nil)

    }
  }

  implicit class ActionListWrapper(list: List[Action]) {
    def flattenTo(classes: Class[_ <: Action]*): List[Action] = {
      list.flatMap {
        case elem: Action if classes.contains(elem.getClass) => List(elem)
        case elem: Action => elem.actions.flattenTo(classes: _*)
      }
    }

    def flattenToReversed(classes: Class[_ <: Action]*): List[Action] = {
      list.reverse.flatMap {
        case elem if classes.contains(elem.getClass) => List(elem)
        case elem: Action => elem.actions.flattenToReversed(classes: _*)
      }
    }
  }
}

