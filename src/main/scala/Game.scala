package model

import model.pieces.{King, Knight,Rook}

import scala.collection.mutable

class Game {
  type AMove = Either[(Piece, (Int, Int)), ((Int, Int), (Int, Int))]
  val tui = new TUI();
  val emptyBoard = new Board();
  val moves: mutable.MutableList[AMove] = new mutable.MutableList[AMove]
  val setKing = Left((new King(Color.Black), (4, 4)))
  val setKnight = Left((new Knight(Color.White), (5, 5)))



  moves += setAction(King(Color.White), (4,0))
  moves += setAction(King(Color.Black), (4,7))
  moves += setAction(Knight(Color.White), (1,0))
  moves += setAction(Knight(Color.White), (6,0))
  moves += setAction(Knight(Color.Black), (1,7))
  moves += setAction(Knight(Color.Black), (6,7))
  moves += setAction(Rook(Color.Black), (0,0))


  def setAction(piece: Piece, pos:(Int, Int)) =
    Left((piece, pos))

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
                p => p.getMoves(from, board)
              } flatMap {
                list => list find {
                  m => m.target == to
                } map {
                  case move: Move => board.set(move.target, piece).set(move.origin, None)
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

