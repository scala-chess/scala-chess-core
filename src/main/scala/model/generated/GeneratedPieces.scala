package model.generated

import model.generated.GeneratedActions._
import model.PieceUtil._

object GeneratedPieces {

  def getByName(name: String): GeneratedPiece =
    name match {
      case __BISHOP.name => __BISHOP
    }

  val hero = __BISHOP

  case object __BISHOP extends GeneratedPiece {
    val id = "B"
    val name = "Bishop"
    val logic = List(__action0)
  }

}