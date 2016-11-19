package model.pieces

import model.TupleUtils._
import model._
import chess.api._

object Queen {

  def apply(color: Color.Value) = new Queen(color, Id.next)

  implicit class QueenLogic(val queen: Queen) extends PieceLogic(queen) {

    override def getActions(field: (Int, Int), board: Board, history: Iterable[Action]): Iterable[Action] =
      Seq(
        (t: (Int, Int)) => t.up.right,
        (t: (Int, Int)) => t.up.left,
        (t: (Int, Int)) => t.down.right,
        (t: (Int, Int)) => t.down.left,
        (t: (Int, Int)) => t.right,
        (t: (Int, Int)) => t.left,
        (t: (Int, Int)) => t.down,
        (t: (Int, Int)) => t.up
      ) flatMap {
        dir => Pattern.line(dir, board, field)
      } filter {
        target => board.get(target) forall {
          !queen.isAlly(_)
        }
      } filter {
        board.isOnBoard
      } map {
        target => Move(queen.id, field, List())
      }
  }

}