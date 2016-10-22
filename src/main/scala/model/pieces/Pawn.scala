package model.pieces

import model.TupleUtils._
import model._

case class Pawn(c: Color.Value) extends Piece(c) {

  override def getMoves(field: (Int, Int), board: Board): Iterable[Action] = {
    val toEmpty = Seq(
      field.straight(this),
      field.straight(this).straight(this)
    ) filter { target => board.get(target).isEmpty 
    } map { target => Move(field, target)}

    val toOccupated = Seq(
      field.straight(this).left,
      field.straight(this).right
    ) filter { target => board.get(target) map {p => !isAlly(p)} getOrElse false
    } map { target => Move(field, target)}

    toEmpty ++ toOccupated
  }
   
}
