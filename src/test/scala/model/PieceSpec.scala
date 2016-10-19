package model

import model.pieces.King
import org.specs2._

class PieceSpec extends Specification { def is = s2"""

  A Piece should
    1. have a color         $color
    2. know its allies      $allies
    3. know its enemies     $enemies
    4. have a nice toString $pieceToString
                          """

  def color = King(Color.White).color must_==Color.White
  def allies = King(Color.White).isAlly(King(Color.White)) must_==true
  def enemies = King(Color.White).isEnemy(King(Color.Black)) must_==true
  def pieceToString = King(Color.White).toString must_== "White-King"

}
