package model

import model.TupleUtils._
import org.specs2.Specification

class PositionTupleSpec extends Specification { def is = s2"""

  A PositionTuple should tell you its
    1. right neighbour   $right
    2. left neighbour  $left
    3. upper neighbour $up
    4. lower neighbour $down
                          """

  def right = (0, 0).right must_==(1, 0)
  def left = (1, 0).left must_==(0, 0)
  def up = (0, 1).up  must_==(0, 0)
  def down = (0, 0).down must_==(0, 1)

}
