package model.pieces

import model.TupleUtils._
import model.{Action, Color, Piece, Move}

case class Knight(c: Color.Value) extends Piece(c) {

  override def getMoves(field: (Int, Int)): Iterable[Action] = Seq(
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
