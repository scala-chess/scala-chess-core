package model

import chess.api._


object ActionFactory {
  def move(pieceId: Int, origin: Position, target: Position, history: History): Move =
    Move(
      pieceId,
      Remove(pieceId, origin),
      Put(pieceId, target),
      history.pieceAt(target) map { removedPiece => Remove(removedPiece.id, target) }
    )


  def castle(firstPieceId: Int, firstOrigin: Position, firstTarget: Position, secondPieceId: Int, secondOrigin: Position, secondTarget: Position, history: History): Castle =
    Castle(firstPieceId,
      move(firstPieceId, firstOrigin, firstTarget, history),
      move(secondPieceId, secondOrigin, secondTarget, history)
    )


  def replace(removePieceId: Int, target: Position, piece: Piece): Replace =
    Replace(
      removePieceId,
      Remove(removePieceId, target),
      PutInitial(target, piece)
    )

  def moveAndReplace(pieceId: Int, origin: Position, target: Position, piece: Piece, choice: String, history: History): MoveAndReplace =
    MoveAndReplace(
      pieceId,
      move(pieceId, origin, target, history),
      replace(pieceId, target, piece),
      choice
    )

  def moveAndReplace(move: Move, choice: String, piece: Piece): MoveAndReplace =
    MoveAndReplace(
      move.pieceId,
      move,
      replace(move.pieceId, move.target, piece),
      choice
    )

}
