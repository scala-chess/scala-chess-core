package model.pieces

import model._
import model.TupleUtils._

case class Knight(c: Color.Value) extends Piece(c) {

  override def getMoves(field: (Int, Int), board: Board): Iterable[Action] = 
    Seq(
      field.right.right.up,
      field.right.right.down,
      field.left.left.up,
      field.left.left.down,
      field.up.up.left,
      field.up.up.right,
      field.down.down.left,
      field.down.down.right
    ) filter { target => board.get(target) forall { !isAlly(_) }
    } map { target => Move(field, target)}
}
