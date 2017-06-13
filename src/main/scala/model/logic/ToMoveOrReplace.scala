package model.logic

import chess.api.{Action, Position}
import model.default.DefaultPieces._
import model.{ActionFactory, History, PieceUtil}


trait ToMoveOrReplace extends Logic {
  // TODO: Make this configurable
  var choices =  Seq(__QUEEN.name, __KNIGHT.name, __ROOK.name, __BISHOP.name)

  override def getActions(field: Position, history: History): Seq[Action] = {
    history.pieceAt(field) map {
      piece =>
        super.getActions(field, history) flatMap {
          action =>
            history.triggerPositions(piece.name).contains(action.target) match {
              case false => Seq(ActionFactory.move(piece.id, field, action.target, history))
              case true => choices map {
                choice => ActionFactory.moveAndReplace(piece.id, field, action.target, PieceUtil.createPiece(piece.color, choice), choice, history)
              }
            }
        }
    }
  } getOrElse Seq()
}
