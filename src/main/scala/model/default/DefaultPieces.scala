package model.default

import model.default.DefaultActions._
import model.PieceUtil._

object DefaultPieces {

  def getByName(name: String): GeneratedPiece =
    name match {
      case __ROOK.name => __ROOK
      case __KING.name => __KING
      case __QUEEN.name => __QUEEN
      case __BISHOP.name => __BISHOP
      case __PAWN.name => __PAWN
      case __KNIGHT.name => __KNIGHT
    }

  val hero = __KING

  case object __ROOK extends GeneratedPiece {
    val id = "R"
    val name = "rook"
    val logic = List(__action0)
  }

  case object __KING extends GeneratedPiece {
    val id = "K"
    val name = "king"
    val logic = List(__action1, __action2)
  }

  case object __QUEEN extends GeneratedPiece {
    val id = "Q"
    val name = "queen"
    val logic = List(__action3)
  }

  case object __BISHOP extends GeneratedPiece {
    val id = "B"
    val name = "bishop"
    val logic = List(__action4)
  }

  case object __PAWN extends GeneratedPiece {
    val id = "P"
    val name = "pawn"
    val logic = List(__action5, __action6, __action7)
  }

  case object __KNIGHT extends GeneratedPiece {
    val id = "N"
    val name = "knight"
    val logic = List(__action8)
  }

}