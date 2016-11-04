
object ListExtensions {
  implicit class TakeUntilListWrapper[T](list: List[T]) {
    def takeUntil(predicate: T => Boolean): List[T] = {
        def rec(tail:List[T], accum:List[T]):List[T] = tail match {
        case Nil => accum.reverse
        case h :: t => rec(if (predicate(h)) t else Nil, h :: accum)
        }
        rec(list, Nil)
    }
  }
}

