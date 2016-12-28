package model

import chess.api.{Color, Piece}
import model.logic._
import model.logic.modifier._
import model.logic.modifier.conditionalMapper.MoveReplaceMapper

object Pieces {
  def getLogic(piece: Piece): Seq[Logic] =
    piece.name match {
      case ROOK =>
        new HorizontalVerticalLine() :: List()
      case KING =>
        new HorizontalVerticalLine() ::
          new DiagonalLine() :: new Castle with Unmoved :: List()
      case QUEEN =>
        new HorizontalVerticalLine() ::
          new DiagonalLine() :: List()
      case BISHOP =>
        new DiagonalLine() :: List()
      case PAWN =>
        new StraightStep() with TargetEmpty with MoveReplaceMapper ::
          new StraightStep(Some(2)) with TargetEmpty with Unmoved with MoveReplaceMapper ::
          new StraightDiagonalStep() with TargetOccupied with MoveReplaceMapper :: List()
      case KNIGHT =>
        new KnightPattern :: List()
    }

  implicit class LogicPiece(piece: Piece) {
    def isAlly(other: Piece) = piece.color == other.color
    def isEnemy(other: Piece) = !isAlly(other)
  }

  val ROOK = "rook"
  val KING = "king"
  val QUEEN = "queen"
  val BISHOP = "bishop"
  val KNIGHT = "knight"
  val PAWN = "pawn"

  def createPiece(color: Color.Value, name: String) = Piece(color, name, Id.next)
  def rook(color: Color.Value) = createPiece(color, ROOK)
  def king(color: Color.Value) = createPiece(color, KING)
  def queen(color: Color.Value) = createPiece(color, QUEEN)
  def bishop(color: Color.Value) = createPiece(color, BISHOP)
  def knight(color: Color.Value) = createPiece(color, KNIGHT)
  def pawn(color: Color.Value) = createPiece(color, PAWN)

  val black: Color.Value = Color.Black
  val white: Color.Value = Color.White
}