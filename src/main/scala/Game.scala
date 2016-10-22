package model

import model.pieces.{King, Knight}

import scala.collection.mutable

class Game {
  type AMove = Either[(Piece, (Int, Int)), ((Int, Int), (Int, Int))]
  val tui = new TUI();
  val emptyBoard = new Board();
  val moves: mutable.MutableList[AMove] = new mutable.MutableList[AMove]
  val setKing = Left((new King(Color.Black), (4, 4)))
  val setKnight = Left((new Knight(Color.Black), (5, 5)))
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
                list => list flatMap {
                  case m: SimpleMove =>Some(m)
                  case other => None
                } find {
                  m => m.to == to
                } map {
                  m =>
                    println(m)
                    val b = board.set(m.to, piece)
                    b.set(m.from, None)
                }
              } getOrElse board
          }
      }
      val newMove = tui.update(board)
      moves += Right(newMove)
    }
  }
}

