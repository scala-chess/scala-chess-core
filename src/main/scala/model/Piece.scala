package model

import chess.api._

abstract class PieceLogic(piece: Piece) {
  override def toString: String = s"${piece.color}-${piece.getClass.getSimpleName}"

  def isAlly(other: Piece) = piece.color == other.color

  def isEnemy(other: Piece) = !isAlly(other)

//  def getActions(field: (Int, Int), board: Board, history: Iterable[Action]): Iterable[Action]

  def handle(board: Board, action: Action): Board =
    action match {
      case move: Move => board.set(action.target, Some(piece)).set(action.origin, None)
      case action: MoveAndChange =>
        val p = action.choice match {
          case MoveAndChangeChoice.Queen => model.pieces.Queen(piece.color)
          case MoveAndChangeChoice.Rook => model.pieces.Rook(piece.color)
          case MoveAndChangeChoice.Bishop => model.pieces.Bishop(piece.color)
          case MoveAndChangeChoice.Knight => model.pieces.Knight(piece.color)
        }
        board.set(action.target, Some(p)).set(action.origin, None)
      case other => board
    }
}


//object PieceLogic {
//  def apply(piece: Piece) =
//    piece match {
//      case p: King => new KingLogic(p)
//      case p: Queen => new QueenLogic(p)
//      case p: Rook => new RookLogic(p)
//      case p: Bishop => new BishopLogic(p)
//      case p: Knight => new KnightLogic(p)
//      case p: Pawn => new PawnLogic(p)
//    }
//}





