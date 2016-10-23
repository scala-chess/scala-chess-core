package model

import model.pieces.{King, Knight, Rook, Bishop, Queen, Pawn}

import scala.collection.mutable

class Game {
  type AMove = Either[(Piece, (Int, Int)), ((Int, Int), (Int, Int))]
  val tui = new TUI();
  val emptyBoard = new Board();
  val moves: mutable.MutableList[AMove] = new mutable.MutableList[AMove]

  moves += setAction(King(Color.Black), (4,0))
  moves += setAction(King(Color.White), (4,7))
  moves += setAction(Knight(Color.Black), (1,0))
  moves += setAction(Knight(Color.Black), (6,0))
  moves += setAction(Knight(Color.White), (1,7))
  moves += setAction(Knight(Color.White), (6,7))
  moves += setAction(Rook(Color.Black), (0,0))
  moves += setAction(Rook(Color.Black), (7,0))
  moves += setAction(Rook(Color.White), (0,7))
  moves += setAction(Rook(Color.White), (7,7))
  moves += setAction(Bishop(Color.Black), (2,0))
  moves += setAction(Bishop(Color.Black), (5,0))
  moves += setAction(Bishop(Color.White), (2,7))
  moves += setAction(Bishop(Color.White), (5,7))
  moves += setAction(Queen(Color.Black), (3,0))
  moves += setAction(Queen(Color.White), (3,7))
  moves += setAction(Pawn(Color.Black), (0,1))
  moves += setAction(Pawn(Color.Black), (1,1))
  moves += setAction(Pawn(Color.Black), (2,1))
  moves += setAction(Pawn(Color.Black), (3,1))
  moves += setAction(Pawn(Color.Black), (4,1))
  moves += setAction(Pawn(Color.Black), (5,1))
  moves += setAction(Pawn(Color.Black), (6,1))
  moves += setAction(Pawn(Color.Black), (7,1))
  moves += setAction(Pawn(Color.White), (0,6))
  moves += setAction(Pawn(Color.White), (1,6))
  moves += setAction(Pawn(Color.White), (2,6))
  moves += setAction(Pawn(Color.White), (3,6))
  moves += setAction(Pawn(Color.White), (4,6))
  moves += setAction(Pawn(Color.White), (5,6))
  moves += setAction(Pawn(Color.White), (6,6))
  moves += setAction(Pawn(Color.White), (7,6))



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
                } flatMap {
                  a => piece map { p => p.handle(board, a)}
                }
              } getOrElse board
          }
      }
      val newMove = tui.update(board)
      moves += Right(newMove)
    }
  }
}

