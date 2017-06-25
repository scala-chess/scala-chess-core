package model.generated

import model.generated.GeneratedActions._
import model.PieceUtil._

object GeneratedPieces {

  def getByName(name: String): GeneratedPiece =
    name match {
      case __BISHOP.name => __BISHOP
case __ROOK.name => __ROOK
case __PAWN.name => __PAWN
    }

  val hero = __BISHOP

  case object __BISHOP extends GeneratedPiece {
    val id = "B"
    val name = "Bishop"
    val logic = List(__action0, __action1)
  }

  case object __ROOK extends GeneratedPiece {
    val id = "R"
    val name = "Rook"
    val logic = List(__action2, __action1)
  }

  case object __PAWN extends GeneratedPiece {
    val id = "P"
    val name = "Pawn"
    val logic = List(__action3)
  }

}