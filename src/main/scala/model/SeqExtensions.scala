package model

import chess.api.Action


object SeqExtensions {

  implicit class SeqWrapper[T](seq: Seq[T]) {
    def flattenTo[T <: Action](classes: Class[_ <: T]*): Seq[T] =
      seq.flatMap {
        case elem: T if classes.contains(elem.getClass) => Seq(elem)
        case elem: Seq[T] => elem.flattenTo(classes: _*)
        case elem: Action => elem.actions.flattenTo(classes: _*)
      }

    def flattenToReversed[T <: Action](classes: Class[_ <: T]*): Seq[T] =
      seq.reverse.flatMap {
        case elem: T if classes.contains(elem.getClass) => Seq(elem)
        case elem: Seq[T] => elem.flattenToReversed(classes: _*)
        case elem: Action => elem.actions.flattenToReversed(classes: _*)
      }

    def takeUntil(predicate: T => Boolean): Seq[T] = {
      def rec(tail: Seq[T], accum: Seq[T]): Seq[T] = tail match {
        case Nil => accum.reverse
        case h :: t => rec(if (predicate(h)) Nil else t, h +: accum)
      }
      rec(seq, Nil)

    }

  }

}

