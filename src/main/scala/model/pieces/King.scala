package model.pieces

import chess.api._
import model.TupleUtils._
import model._

object King {

  def apply(color: Color.Value) = new King(color, Id.next)

  implicit class KingLogic(val king: King) extends PieceLogic(king) {
    override def getActions(field: (Int, Int), board: Board, history: Iterable[Action]): Iterable[Action] = {
      val move = Seq(
        field.right,
        field.left,
        field.up,
        field.down,
        field.up.left,
        field.up.right,
        field.down.left,
        field.down.right
      ) filter {
        target => board.get(target) forall {
          !king.isAlly(_)
        }
      } filter {
        board.isOnBoard
      } map { target => Move(king.id, field, List()) }

      val rookPositions = History.unmoved(history, king) match {
        case true => board.getAll flatMap {
          case ((x, y), rook@chess.api.Rook(color, _)) if color == king.color && History.unmoved(history, rook) && y == field._2 => Some((x, y))
          case _ => None
        }
        case false => Iterable()
      }

      val castle = rookPositions map {
        rookPosition => (rookPosition, directionFromXDiff(field, rookPosition))
      } filter {
        t =>
          val rookPosition = t._1
          val piecesBetween = betweenX(field, rookPosition) flatMap { f => board.get(f) }
          piecesBetween.isEmpty
      } map {
        t =>
          val rookPosition = t._1
          val dir = t._2
//          Castle(king.id, field, rookPosition, dir(dir(field)), dir(field))
          Castle(king.id, field, List())
      }

      move ++ castle
    }

    def directionFromXDiff(from: (Int, Int), to: (Int, Int)): ((Int, Int)) => (Int, Int) =
      from._1 match {
        case x if x > to._1 => (t: (Int, Int)) => t.left
        case x if x < to._1 => (t: (Int, Int)) => t.right
        case _ => (t: (Int, Int)) => t
      }

    def betweenX(start: (Int, Int), end: (Int, Int)): List[(Int, Int)] = {
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
            king <- board.get(castle.origin) collect { case king: King => king }
            rook <- board.get(castle.target) collect { case rook: Rook => rook }
          } yield board
//            .set(castle.targetKing, Some(king))
//            .set(castle.targetRook, Some(rook))
            .set(castle.target, None)
            .set(castle.origin, None)
          optionBoard getOrElse board
        case _ => super.handle(board, action)
      }
  }

}
