package model.pieces

import model.TupleUtils._
import model._

case class King(c: Color.Value) extends Piece(c) {

  override def getMoves(field: (Int, Int)): Iterable[Action] = Seq(
    field.right,
    field.left,
    field.up,
    field.down,
    field.up.left,
    field.up.right,
    field.down.left,
    field.down.right
  ).map(Move(field, _))

}
