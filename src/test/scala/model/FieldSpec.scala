package model

import org.specs2.Specification


class FieldSpec extends Specification { def is = s2"""

  This is a specification for the Field class

  A Field should
    1. have a right neighbour           $right
    2. have a left neighbour            $left
    3. have an upper neighbour          $upper
    4. have a lower neighbour           $lower
    5. have an upper right neighbour    $upperRight
    6. have an upper left neighbour     $upperLeft
    7. have an lower right neighbour    $lowerRight
    8. have an lower left neighbour     $lowerLeft
                            """

  def right = {
    Field(0, 0).getRightNeighbour must_==Field(1, 0)
  }

  def left = {
    Field(2, 2).getLeftNeighbour must_==Field(1, 2)
  }

  def upper = {
    Field(3, 5).getUpperNeighbour must_==Field(3, 4)
  }

  def lower = {
    Field(4, 2).getLowerNeighbour must_==Field(4, 3)
  }

  def upperRight = {
    Field(3, 3).getUpperRightNeighbour must_==Field(4, 2)
  }

  def upperLeft = {
    Field(3, 3).getUpperLeftNeighbour must_==Field(2, 2)
  }

  def lowerRight = {
    Field(3, 3).getLowerRightNeighbour must_==Field(4, 4)
  }

  def lowerLeft = {
    Field(3, 3).getLowerLeftNeighbour must_==Field(2, 4)
  }

}
