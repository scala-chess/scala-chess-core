package model

import org.specs2._

class PieceSpec extends Specification { def is = s2"""

  A Piece should
    have a color       $color
    know its allies    $allies
    know its enemies   $enemies
                       """

  def color = {
    val piece = King(Color.White)
    piece.color must_==Color.White
  }

  def allies = {
    val piece = King(Color.White)
    val ally = King(Color.White)
    piece.isAlly(ally) must_==true
  }

  def enemies = {
    val piece = King(Color.White)
    val enemy = King(Color.Black)
    piece.isEnemy(enemy) must_==true
  }

}
