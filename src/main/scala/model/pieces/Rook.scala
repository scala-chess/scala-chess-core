package model.pieces

import model.TupleUtils._
import model._
import chess.api._

object Rook {
  implicit class RookLogic(val rook: Rook) extends PieceLogic(rook) {
    override def getActions(field: (Int, Int), board: Board): Iterable[Action] =
    Seq(
      (t:(Int,Int)) => t.right,
      (t:(Int,Int)) => t.left,
      (t:(Int,Int)) => t.down,
      (t:(Int,Int)) => t.up
    ) flatMap {
      dir => Pattern.line(dir, board, field)
    } filter { 
      target => board.get(target) forall { !isAlly(_) }
    } map { 
      target => Move(field, target)
    }
  }
}
