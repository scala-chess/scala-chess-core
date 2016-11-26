package model

import org.specs2.Specification
import org.specs2.specification.core.SpecStructure

class IdSpec extends Specification {
  def is: SpecStructure =
    s2"""
        The Id object should
          generate incremental ids $ids
      """

  val id1 = Id.next
  val id2 = Id.next

  def ids = id1 must_!= id2
}
