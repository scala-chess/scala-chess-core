package model.logic.move

import chess.api.Action
import model.actions.ActionFactory
import model.{History, Pattern}
import model.ListExtensions._
import model.TupleUtils._

class HorizontalVertical() extends Logic {
  override def getActions(field: (Int, Int), history: History): List[Action] =
    history.pieceAt(field) map {
      piece =>
        List(
          (t: (Int, Int)) => t.up,
          (t: (Int, Int)) => t.left,
          (t: (Int, Int)) => t.right,
          (t: (Int, Int)) => t.down
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
