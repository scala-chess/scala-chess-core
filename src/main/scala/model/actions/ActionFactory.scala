package model.actions

import chess.api._
import model.History


object ActionFactory {
  def move(origin: Position, target: Position, history: History): chess.api.Move = {
    history.pieceAt(origin) map {
      movedPiece => {
        val actions = List(
          Some(Remove(movedPiece.id, origin)),
          history.pieceAt(target) map {
            removedPiece => Remove(removedPiece.id, target)
          },
          Some(Put(movedPiece.id, target))
        ) collect { case Some(a) => a }
        chess.api.Move(movedPiece.id, target, actions)
      }

      } get
  }
}
