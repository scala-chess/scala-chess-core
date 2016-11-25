package model

import org.specs2.Specification

class PatternSpec extends Specification {
  override def is =
    s2"""
        Pattern should generate a direction from
          two equal positions $dirEqual
          two positions with same x $dirSameX
          two positions with same y $dirSameY
          two positions with same absolute x and y diff $dirSameXYDiff
          two arbitrary positions $arbitrary

        Pattern should generate a
          line of positions = $line



      """

  def ZERO = (0, 0)

  def dirEqual = Pattern.direction(ZERO, ZERO)(ZERO) must_== ZERO

  def dirSameX = Pattern.direction(ZERO, (0, 2))(ZERO) must_==(0, 1)

  def dirSameY = Pattern.direction(ZERO, (2, 0))(ZERO) must_==(1, 0)

  def dirSameXYDiff = Pattern.direction(ZERO, (2, -2))(ZERO) must_==(1, -1)

  def arbitrary = Pattern.direction(ZERO, (2, 3))(ZERO) must_==(2,3)

  val dir = Pattern.direction(ZERO, (2, 2))

  def line = Pattern.line(dir, ZERO, 3) must containTheSameElementsAs(List((1,1), (2,2), (3,3)))
}
