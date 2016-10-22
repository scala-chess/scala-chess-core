package model.pieces

import model.TupleUtils._
import model._

case class Rook(c: Color.Value) extends Piece(c) {

  override def getMoves(field: (Int, Int), board: Board): Iterable[Action] = Seq(
    field.right.right.up,
    field.right.right.down,
    field.left.left.up,
    field.left.left.down,
    field.up.up.left,
    field.up.up.right,
    field.down.down.left,
    field.down.down.right
  ).map(Move(field, _))

}
