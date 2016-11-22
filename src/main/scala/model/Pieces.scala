package model

import chess.api.{Bishop, Color, King, Knight, Pawn, Piece, Queen, Rook}
import model.logic._
import model.logic.modifier.{TargetEmpty, TargetOccupied, Unmoved}

object Pieces {
  def getLogic(piece: Piece, boardSize: Int): Seq[Logic] =
    piece match {
      case _: Rook => new HorizontalVerticalLine() :: List()
      case _: King => new HorizontalVerticalLine() :: new DiagonalLine() :: new Castle with Unmoved :: List()
      case _: Queen => new HorizontalVerticalLine() :: new DiagonalLine() :: List()
      case _: Bishop => new DiagonalLine() :: List()
      case _: Pawn => new StraightStep() with TargetEmpty :: new StraightStep(Some(2)) with TargetEmpty with Unmoved :: new StraightDiagonalStep() with TargetOccupied :: List()
      case _: Knight => new KnightPattern :: List()
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