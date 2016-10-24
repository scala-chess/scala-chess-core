package model.pieces

import model.TupleUtils._
import model._

case class Pawn(c: Color.Value, unmoved: Boolean) extends Piece(c) {

  override def getMoves(field: (Int, Int), board: Board): Iterable[Action] = {
    val toEmpty = Seq(
      field.straight(this)
    ) filter { target => board.get(target).isEmpty 
    } map { target => Move(field, target)}

    val toEmpty2 = Seq(
      field.straight(this).straight(this)      
    ) filter { target => board.get(target).isEmpty && unmoved 
    } map { target => Move(field, target)}

    val toOccupated = Seq(
      field.straight(this).left,
      field.straight(this).right
    ) filter { target => board.get(target) exists { !isAlly(_) }
    } map { target => Move(field, target)}

    toEmpty ++ toEmpty2 ++ toOccupated
  }

  override def handle(board: Board, action: Action): Board = {
    board.set(action.target, Some(Pawn(c, unmoved = false))).set(action.origin, None)
  }   
}

object Pawn {
  def apply(c: Color.Value) = new Pawn(c, true)
}
