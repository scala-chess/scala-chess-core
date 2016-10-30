package model.pieces

import chess.api._
import model.TupleUtils._
import model._

object Bishop {

  implicit class BishopLogic(val bishop: Bishop) extends PieceLogic(bishop) {
    override def getActions(field: (Int, Int), board: Board, history: Iterable[Action]): Iterable[Action] =
      Seq(
        (t: (Int, Int)) => t.up.right,
        (t: (Int, Int)) => t.up.left,
        (t: (Int, Int)) => t.down.right,
        (t: (Int, Int)) => t.down.left
      ) flatMap {
        dir => Pattern.line(dir, board, field)
      } filter {
        target => board.get(target) forall {
          !bishop.isAlly(_)
        }
      } map {
        target => Move(bishop.id, field, target)
      }
  }

  def apply(color: Color.Value) = new Bishop(color, Id.next)
}

