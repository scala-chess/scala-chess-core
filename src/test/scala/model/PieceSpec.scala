package model

import org.specs2._

class PieceSpec extends Specification { def is = s2"""

  A Piece should
    1. have a color         $color
    2. know its allies      $allies
    3. know its enemies     $enemies
    4. have a nice toString $pieceToString
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

  def pieceToString = {
    val piece = King(Color.White)
    piece.toString === "White-King"
  }

}
