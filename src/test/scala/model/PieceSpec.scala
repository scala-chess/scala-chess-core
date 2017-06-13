package model

import chess.api._
import org.specs2._
import model.default.DefaultPieces._
import model.util.Colors._

class PieceSpec extends Specification { def is = s2"""

  A Piece should
    1. have a color         $color
                          """

  def color = __KING(white).color must_==Color.White

}
