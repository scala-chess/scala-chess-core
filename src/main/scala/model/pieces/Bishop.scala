package model.pieces

import model.TupleUtils._
import model._

case class Bishop(c: Color.Value) extends Piece(c) {
  override def getMoves(field: (Int, Int), board: Board): Iterable[Action] = 
    Seq(
      (t:(Int,Int)) => t.up.right,
      (t:(Int,Int)) => t.up.left,
      (t:(Int,Int)) => t.down.right,
      (t:(Int,Int)) => t.down.left
    ) flatMap {
      dir => line(dir, board, field, 0)
    } filter { 
      target => board.get(target) forall { !isAlly(_) }
    } map { 
      target => Move(field, target)
    }
}
