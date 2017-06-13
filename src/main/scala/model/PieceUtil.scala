package model

import chess.api.{Color, Piece}
import model.config.Pieces.getByName
import model.logic.Logic


object PieceUtil {

  implicit class LogicPiece(piece: Piece) {
    def isAlly(other: Piece): Boolean = piece.color == other.color

    def isEnemy(other: Piece): Boolean = !isAlly(other)
  }

  def getLogic(piece: Piece): Seq[Logic] = getByName(piece.name).logic

  def getId(name: String): String = getByName(name).id

  def createPiece(color: Color.Value, name: String) = Piece(color, name, Id.next)

  trait GeneratedPiece {
    val name: String
    val logic: Seq[Logic]
    val id: String

    def apply(color: Color.Value): Piece = createPiece(color, name)
  }

}
