package model.pieces

import model._
import model.TupleUtils._

case class King(c: Color.Value) extends Piece(c) {

  override def getMoves(field: (Int, Int)): Iterable[Move] = Seq(
    field.right,
    field.left,
    field.up,
    field.down,
    field.up.left,
    field.up.right,
    field.down.left,
    field.down.right
  ).map(SimpleMove(field, _))

}
