package model

import chess.api.{Color, Piece, Position}
import model.TupleUtils._
import model.logic._
import model.logic.modifier._

object Pieces {
  def getLogic(piece: Piece): Seq[Logic] =
    piece.name match {
      case ROOK => List(
        new Line(directions = horizontalVertical) with TargetEmptyOrEnemy with ToMove
      )

      case KING => List(
        new Line(directions = horizontalVertical ++ diagonal, maxSteps = Some(1)) with TargetEmptyOrEnemy with ToMove,
        new WherePieces(pieceRestriction = Seq(ROOK), enemy = false)
          with Unmoved with TargetUnmoved with OnSameRow with EmptyBetween with ToCastle
      )

      case QUEEN => List(
        new Line(directions = horizontalVertical ++ diagonal) with TargetEmptyOrEnemy with ToMove
      )

      case BISHOP => List(
        new Line(directions = diagonal) with TargetEmptyOrEnemy with ToMove
      )

      case PAWN => List(
        new Step(directions = vertical) with TargetEmpty with Forward with ToMove,
        new Step(directions = vertical, Some(2)) with TargetEmpty with Forward with Unmoved with ToMove,
        new Step(directions = diagonal) with TargetEnemy with Forward with ToMove
      )

      case KNIGHT => List(
        new Step(directions = knightPattern) with TargetEmptyOrEnemy with ToMove
      )
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

  val diagonal = Seq(
    (t: Position) => t.up.right,
    (t: Position) => t.up.left,
    (t: Position) => t.down.right,
    (t: Position) => t.down.left
  )

  val vertical = Seq(
    (t: Position) => t.down,
    (t: Position) => t.up
  )

  val horizontal = Seq(
    (t: Position) => t.left,
    (t: Position) => t.right
  )

  val horizontalVertical = vertical ++ horizontal

  val knightPattern = Seq(
    (t: Position) => t.right.right.up,
    (t: Position) => t.right.right.down,
    (t: Position) => t.left.left.up,
    (t: Position) => t.left.left.down,
    (t: Position) => t.up.up.left,
    (t: Position) => t.up.up.right,
    (t: Position) => t.down.down.left,
    (t: Position) => t.down.down.right
  )
}