package model.actions

import chess.api._
import model.History


object ActionFactory {
  def move(pieceId: Int, origin: Position, target: Position, history: History): Move = {
    val actions = List(
      Some(Remove(pieceId, origin)),
      history.pieceAt(target) map {
        removedPiece => Remove(removedPiece.id, target)
      },
      Some(Put(pieceId, target))
    ) collect { case Some(a) => a }

    Move(pieceId, target, actions, Some(origin))
  }

  def castle(firstPieceId: Int, firstOrigin: Position, firstTarget: Position, secondPieceId: Int, secondOrigin: Position, secondTarget:Position, history: History): Castle = {
    val actions = List(
      move(firstPieceId, firstOrigin, firstTarget, history),
      move(secondPieceId, secondOrigin, secondTarget, history)
    )
    Castle(firstPieceId, secondOrigin, actions, Some(firstOrigin))
  }

}
