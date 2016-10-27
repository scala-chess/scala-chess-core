package model.pieces

import model.TupleUtils._
import model._
import chess.api._

object Pawn {

  def apply(color: Color.Value) = new Pawn(color, Id.next)

  implicit class PawnLogic(val pawn: Pawn) extends PieceLogic(pawn) {
    override def getActions(field: (Int, Int), board: Board): Iterable[Action] = {
      val toEmpty = Seq(
        field.straight(pawn)
      ) filter { target => board.get(target).isEmpty 
      } map { target => Move(pawn.id, field, target)}

      val toEmpty2 = Seq(
        field.straight(pawn).straight(pawn)
      ) filter { target => board.get(target).isEmpty && unmoved 
      } map { target => Move(pawn.id, field, target)}

      val toOccupated = Seq(
        field.straight(pawn).left,
        field.straight(pawn).right
      ) filter { target => board.get(target) exists { !pawn.isAlly(_) }
      } map { target => Move(pawn.id, field, target)}

      toEmpty ++ toEmpty2 ++ toOccupated
    }
    
    def unmoved = true

  }
}