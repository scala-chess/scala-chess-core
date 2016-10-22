package model

import model.pieces.{King, Knight}

import scala.collection.mutable

class Game {
  type AMove = Either[(Piece, (Int, Int)), ((Int, Int), (Int, Int))]
  val tui = new TUI();
  val emptyBoard = new Board();
  val moves: mutable.MutableList[AMove] = new mutable.MutableList[AMove]
  val setKing = Left((new King(Color.Black), (4, 4)))
  val setKnight = Left((new Knight(Color.White), (5, 5)))
  moves += setKing
  moves += setKnight

  def run = {
    while (true) {
      val board = moves.foldLeft(emptyBoard) {
        (board, move) =>
          move match {
            case Left(left) => board.set(left._2, Some(left._1))
            case Right(right) =>
              val from = right._1
              val to = right._2
              val piece = board.get(from)
              piece map {
                p => p.getMoves(from)
              } flatMap {
                list => list find {
                  m => m.target == to
                } map {
                  case move: Move =>
                    val isAlly = (for {
                      p1 <- piece
                      p2 <- board.get(move.target)
                    } yield p1.isAlly(p2)) getOrElse false

                    isAlly match {
                      case true => board
                      case false => board.set(move.target, piece).set(move.origin, None)
                    }
                  case other => board
                }
              } getOrElse board
          }
      }
      val newMove = tui.update(board)
      moves += Right(newMove)
    }
  }
}

