package model.pieces

import model.TupleUtils._
import model._

case class Rook(c: Color.Value) extends Piece(c) {
  override def getMoves(field: (Int, Int), board: Board): Iterable[Action] = 
    Seq(
      (t:(Int,Int)) => t.right,
      (t:(Int,Int)) => t.left,
      (t:(Int,Int)) => t.down,
      (t:(Int,Int)) => t.up
    ) flatMap {
      dir => line(dir, board, field, 0)
    } filter { 
      target => board.get(target) map {p => !isAlly(p)} getOrElse true 
    } map { 
      target => Move(field, target)
    }  
}
