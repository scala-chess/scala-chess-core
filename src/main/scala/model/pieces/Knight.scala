package model.pieces

import chess.api._
import model.TupleUtils._
import model._

object Knight {

  def apply(color: Color.Value) = new Knight(color, Id.next)

  implicit class KnightLogic(val knight: Knight) extends PieceLogic(knight) {
    override def getActions(field: (Int, Int), board: Board, history: Iterable[Action]): Iterable[Action] =
      Seq(
        field.right.right.up,
        field.right.right.down,
        field.left.left.up,
        field.left.left.down,
        field.up.up.left,
        field.up.up.right,
        field.down.down.left,
        field.down.down.right
      ) filter {
        target => board.get(target) forall {
          !knight.isAlly(_)
        }
      } filter {
        board.isOnBoard
      } map { target => Move(knight.id, field, target) }
  }

}