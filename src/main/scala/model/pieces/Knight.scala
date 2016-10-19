package model.pieces

import model.{Color, Move, Piece, SimpleMove}
import model.TupleUtils._

class Knight(c: Color.Value) extends Piece(c) {

  override def getMoves(field: (Int, Int)): Iterable[Move] = Seq(
    field.right.right.up,
    field.right.right.down,
    field.left.left.up,
    field.left.left.down,
    field.up.up.left,
    field.up.up.right,
    field.down.down.left,
    field.down.down.right
  ).map(SimpleMove(field, _))
}
