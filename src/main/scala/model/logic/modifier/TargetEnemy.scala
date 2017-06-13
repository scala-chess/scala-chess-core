package model.logic.modifier

import chess.api.Action
import model.History
import model.logic.Logic
import model.PieceUtil.LogicPiece

trait TargetEnemy extends Logic {
  override def getActions(field: (Int, Int), history: History): Seq[Action] =
    super.getActions(field, history) filter {
      action =>
        history.pieceAt(field) forall {
          piece =>
            history.pieceAt(action.target) exists {
              _.isEnemy(piece)
            }
        }
    }
}
