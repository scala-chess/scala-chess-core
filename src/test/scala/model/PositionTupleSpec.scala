package model

import chess.api.{Color, Piece}
import model.TupleUtils._
import org.specs2.Specification
import model.Pieces._

class PositionTupleSpec extends Specification {
  def is =
    s2"""

  A PositionTuple should tell you its
    right neighbour   $right
    left neighbour    $left
    upper neighbour   $up
    lower neighbour   $down
    straight neighbour of piece $straight

  A PositionTuple should tell you if another position
    is on the same row $sameRow
    is on the same column $sameColumn

  It should also give you its
    x value $x
    y value $y

  A PositionTuple should handle basic math like
    subtraction with a tuple $sub
    addition with a tuple $add
    absolute of a tuple $abs

                          """

  def right = (0, 0).right must_==(1, 0)

  def left = (1, 0).left must_==(0, 0)

  def up = (0, 1).up must_==(0, 0)

  def down = (0, 0).down must_==(0, 1)

  def straight = (0, 0).straight(pawn(black)) must_==(0, 1)

  def sameRow = (0, 2).isSameColumn((0, 1)) must_== true

  def sameColumn = (0, 1).isSameRow((1, 1)) must_== true

  def x = (0, 1).x must_== 0

  def y = (0, 1).y must_== 1

  def sub = (2, 1) - (1, 1) must_==(1, 0)

  def add = (0, 1) + (1, 1) must_==(1, 2)

  def abs = (-2, 1).abs must_==(2, 1)


}
