package model.pieces

import model.TupleUtils._
import model._

case class Rook(c: Color.Value, unmoved: Boolean) extends Piece(c) {
  override def getMoves(field: (Int, Int), board: Board): Iterable[Action] = 
    Seq(
      (t:(Int,Int)) => t.right,
      (t:(Int,Int)) => t.left,
      (t:(Int,Int)) => t.down,
      (t:(Int,Int)) => t.up
    ) flatMap {
      dir => line(dir, board, field, 0)
    } filter { 
      target => board.get(target) forall { !isAlly(_) }
    } map { 
      target => Move(field, target)
    }

    override def handle(board: Board, action: Action): Board = 
    action match {
      case move: Move => board.set(move.target, Some(this.copy(unmoved = false))).set(move.origin, None)
      case _ => super.handle(board, action)
    }      

    
}

object Rook {
  def apply(c: Color.Value) = new Rook(c, true)
}