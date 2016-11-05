package model

import chess.api._

object History {

  def unmoved(history: Iterable[Action], piece: Piece): Boolean = {
    !(history exists {
      case x: Move => x.pieceId == piece.id
      case x: Castle => x.pieceId == piece.id
      case _ => false
    }
      )
  }

}