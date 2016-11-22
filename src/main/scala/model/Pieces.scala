package model

import chess.api.{Bishop, Color, King, Knight, Pawn, Piece, Queen, Rook}
import model.logic._
import model.logic.modifier.{TargetEmpty, TargetOccupied, Unmoved}

object Pieces {
  def getLogic(piece: Piece, boardSize: Int): Seq[Logic] =
    piece match {
      case _: Rook => new HorizontalVerticalLine(boardSize) :: List()
      case _: King => new HorizontalVerticalLine(1) :: new DiagonalLine(1) :: new Castle with Unmoved :: List()
      case _: Queen => new HorizontalVerticalLine(boardSize) :: new DiagonalLine(boardSize) :: List()
      case _: Bishop => new DiagonalLine(boardSize) :: List()
      case _: Pawn => new StraightStep(1) with TargetEmpty :: new StraightStep(2) with TargetEmpty with Unmoved :: new StraightDiagonalStep(1) with TargetOccupied :: List()
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