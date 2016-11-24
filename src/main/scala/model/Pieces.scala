package model

import chess.api.{Bishop, Color, King, Knight, Move, MoveAndReplace, Pawn, Piece, Queen, Rook}
import model.logic._
import model.logic.modifier._
import model.logic.modifier.conditionalMapper.MoveReplaceMapper

object Pieces {
  def getLogic(piece: Piece): Seq[Logic] =
    piece match {
      case _: Rook =>
        new HorizontalVerticalLine() :: List()
      case _: King =>
        new HorizontalVerticalLine() ::
          new DiagonalLine() :: new Castle with Unmoved :: List()
      case _: Queen =>
        new HorizontalVerticalLine() ::
          new DiagonalLine() :: List()
      case _: Bishop =>
        new DiagonalLine() :: List()
      case _: Pawn =>
        new StraightStep() with TargetEmpty with MoveReplaceMapper ::
          new StraightStep(Some(2)) with TargetEmpty with Unmoved with MoveReplaceMapper ::
          new StraightDiagonalStep() with TargetOccupied with MoveReplaceMapper :: List()
      case _: Knight =>
        new KnightPattern :: List()
    }

  implicit class LogicPiece(piece: Piece) {
    def isAlly(other: Piece) = piece.color == other.color
    def isEnemy(other: Piece) = !isAlly(other)
  }
}

object King {
  def apply(color: Color.Value) = new King(color, Id.next)
}

object Queen {
  def apply(color: Color.Value) = new Queen(color, Id.next)
}

object Rook {
  def apply(color: Color.Value) = new Rook(color, Id.next)
}

object Pawn {
  def apply(color: Color.Value) = new Pawn(color, Id.next)
}

object Knight {
  def apply(color: Color.Value) = new Knight(color, Id.next)
}

object Bishop {
  def apply(color: Color.Value) = new Bishop(color, Id.next)
}