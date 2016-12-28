package model

import chess.api._
import org.specs2._
import model.Pieces._

class PieceSpec extends Specification { def is = s2"""

  A Piece should
    1. have a color         $color
                          """

  def color = king(white).color must_==Color.White

}
