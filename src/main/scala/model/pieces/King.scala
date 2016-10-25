package model.pieces

import model.TupleUtils._
import model._

case class King(c: Color.Value, unmoved: Boolean) extends Piece(c) {

  override def getMoves(field: (Int, Int), board: Board): Iterable[Action] = {
    val move = Seq(
      field.right,
      field.left,
      field.up,
      field.down,
      field.up.left,
      field.up.right,
      field.down.left,
      field.down.right
    ) filter { target => board.get(target) forall { !isAlly(_) }
    } map { target => Move(field, target)}

    val rookPositions = unmoved match {
      case true => board.getAll flatMap { 
          case ((x,y),Rook(color,unmoved)) if color == this.c && unmoved == true && y == field._2 => Some((x,y))
          case _ => None
        }
      case false => Iterable()
    }
    
    val castle = rookPositions map {
      rookPosition => (rookPosition, directionFromXDiff(field, rookPosition))
    } filter {
      t =>        
        val rookPosition = t._1
        betweenX(field, rookPosition) flatMap { f => board.get(f) }.isEmpty
    } map {
      t =>        
        val rookPosition = t._1
        val dir = t._2 
        Castle(field, rookPosition, dir(dir(field)), dir(field))
    }
    
    move ++ castle
  }

  def directionFromXDiff(from: (Int,Int), to: (Int,Int)): ((Int,Int)) => (Int,Int) =
    from._1 match {
        case x if x > to._1 => (t:(Int,Int)) => t.left
        case x if x < to._1 => (t:(Int,Int)) => t.right
        case _ => (t:(Int,Int)) => t
      } 

  def betweenX(start: (Int,Int), end: (Int,Int)): List[(Int,Int)] = {
    val dir = directionFromXDiff(start, end)
    start._1 match {
      case x if x == end._1 || dir(start)._1 == end._1 => List() 
      case x => dir(start) :: betweenX(dir(start), end)
    }
  }

  override def handle(board: Board, action: Action): Board = 
    action match {
      case castle: Castle =>
        val optionBoard = for {
          king <- board.get(castle.origin) collect {case king: King => king }
          rook <- board.get(castle.target) collect {case rook: Rook => rook }
        } yield board
                  .set(castle.targetKing, Some(king.copy(unmoved = false)))
                  .set(castle.targetRook, Some(rook.copy(unmoved = false)))
                  .set(castle.target, None)
                  .set(castle.origin, None) 
        optionBoard getOrElse board
      case move: Move => board.set(move.target, Some(this.copy(unmoved = false))).set(move.origin, None)
    } 
}

object King {
  def apply(c:Color.Value) = new King(c, true)
}
