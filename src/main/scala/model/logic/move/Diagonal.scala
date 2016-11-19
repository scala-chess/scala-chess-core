package model.logic.move

import chess.api.{Action, Position}
import model.ListExtensions._
import model.TupleUtils._
import model.actions.ActionFactory
import model.{History, Pattern}

class Diagonal extends Logic {
  override def getActions(field: Position, history: History): List[Action] =
    history.pieceAt(field) map {
      piece =>
        List(
          (t: (Int, Int)) => t.up.right,
          (t: (Int, Int)) => t.up.left,
          (t: (Int, Int)) => t.down.right,
          (t: (Int, Int)) => t.down.left
        ) flatMap {
          dir => Pattern.line(dir, field, 8) takeUntil (pos => history.pieceAt(pos).isDefined)
        } filter {
          target => history.pieceAt(target) forall {
            targetPiece => targetPiece.color != piece.color
          }
          //        } filter {
          //          history.isOnBoard
        } map {
          target => ActionFactory.move(field, target, history)
        }
    } getOrElse List()
}
