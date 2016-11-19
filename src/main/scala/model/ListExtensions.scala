package model

import chess.api.Atomic

object ListExtensions {

  implicit class TakeUntilListWrapper[T](list: List[T]) {
    def takeUntil(predicate: T => Boolean): List[T] = {
      def rec(tail: List[T], accum: List[T]): List[T] = tail match {
        case Nil => accum.reverse
        case h :: t => rec(if (predicate(h)) t else Nil, h :: accum)
      }
      rec(list, Nil)

    }

    def flattenTo(classes: Class[_ <: T]*): List[T] = {
      list.flatMap {
        case elem if classes.contains(elem.getClass) => List(elem)
        case elem: Atomic => List.empty
        case elem: List[T] => elem.flattenTo(classes: _*)
      }
    }
  }

}

