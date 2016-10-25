package model

import chess.api._

abstract class PieceLogic(val piece: Piece) {
  override def toString: String = s"${piece.color}-${piece.getClass.getSimpleName}"
  def isAlly(other: Piece) = piece.color == other.color
  def isEnemy(other: Piece) = !isAlly(other)
  def getActions(field: (Int, Int), board: Board): Iterable[Action]
  
  def handle(board: Board, action: Action): Board =
    action match {
      case move: Move => board.set(action.target, Some(piece)).set(action.origin, None)
      case other => board
    }  
}




