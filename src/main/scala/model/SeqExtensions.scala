package model


object SeqExtensions {

  implicit class SeqWrapper[T](seq: Seq[T]) {
    def flattenTo(classes: Class[_ <: T]*): Seq[T] =
      seq.flatMap {
        case elem: T if classes.contains(elem.getClass) => Seq(elem)
        case elem: Seq[T] => elem.flattenTo(classes: _*)
      }

    def flattenToReversed(classes: Class[_ <: T]*): Seq[T] =
      seq.reverse.flatMap {
        case elem: T if classes.contains(elem.getClass) => Seq(elem)
        case elem: Seq[T] => elem.flattenToReversed(classes: _*)
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

