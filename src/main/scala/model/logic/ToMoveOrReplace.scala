package model.logic

import chess.api.{Action, Position}
import model.{ActionFactory, History, Pieces}


trait ToMoveOrReplace extends Logic {

  val choices = Seq(Pieces.QUEEN, Pieces.KNIGHT, Pieces.ROOK, Pieces.BISHOP)

  override def getActions(field: Position, history: History): Seq[Action] = {
    history.pieceAt(field) map {
      piece =>
        super.getActions(field, history) flatMap {
          action =>
            history.triggerPositions(piece.name).contains(action.target) match {
              case false => Seq(ActionFactory.move(piece.id, field, action.target, history))
              case true => choices map {
                choice => ActionFactory.moveAndReplace(piece.id, field, action.target, Pieces.createPiece(piece.color, choice), choice, history)
              }
            }
        }
    }
  } getOrElse Seq()
}
