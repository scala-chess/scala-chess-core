package model

import chess.api._
import org.specs2._

class PieceSpec extends Specification { def is = s2"""

  A Piece should
    1. have a color         $color
                          """

  def color = model.King(Color.White).color must_==Color.White

}
