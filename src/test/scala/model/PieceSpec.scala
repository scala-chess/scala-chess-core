package model

import chess.api._
import model.pieces.King
import org.specs2._

class PieceSpec extends Specification { def is = s2"""

  A Piece should
    1. have a color         $color

    4. have a nice toString $pieceToString
                          """

  def color = King(Color.White).color must_==Color.White
  def pieceToString = King(Color.White).toString must_== "White-King"

}
