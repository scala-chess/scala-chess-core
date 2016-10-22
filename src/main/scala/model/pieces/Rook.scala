package model.pieces

import model.TupleUtils._
import model._

case class Rook(c: Color.Value) extends Piece(c) {
  type Direction = ((Int,Int)) => (Int,Int)
  override def getMoves(field: (Int, Int), board: Board): Iterable[Action] = 
    Seq[Direction](
      (t:(Int,Int)) => t.right,
      (t:(Int,Int)) => t.left,
      (t:(Int,Int)) => t.down,
      (t:(Int,Int)) => t.up
    ) flatMap {
      dir => line(dir, board, field, 0)
    } filter { 
      target => board.get(target) map {p => !isAlly(p)} getOrElse true 
    } map { 
      target => Move(field, target)
    }


  def line(dir: Direction, board: Board, pos: (Int,Int), depth: Int): List[(Int,Int)] = {
    val target = dir(pos) 
    if(depth > 8){
      List()
    } else {
      board.get(target) match {
        case None => target :: line(dir, board, target, depth + 1)
        case Some(piece) => List(target)
      }
    }    
  }

  def right(t:(Int,Int)):(Int,Int) = {
    t.right
  }
}
