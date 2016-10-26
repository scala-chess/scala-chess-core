
import chess.api._
import model.pieces._
import model.Piece._
import model.Board

import scala.collection.mutable

class Game {
  type AMove = Either[(Piece, (Int, Int)), ((Int, Int), (Int, Int))]
  val tui = new TUI()
  val emptyBoard = new Board()
  val moves: mutable.MutableList[AMove] = new mutable.MutableList[AMove]

  moves += setAction(chess.api.King(Color.Black), (4, 0))
  moves += setAction(chess.api.King(Color.White), (4, 7))
  moves += setAction(chess.api.Knight(Color.Black), (1, 0))
  moves += setAction(chess.api.Knight(Color.Black), (6, 0))
  moves += setAction(chess.api.Knight(Color.White), (1, 7))
  moves += setAction(chess.api.Knight(Color.White), (6, 7))
  moves += setAction(chess.api.Rook(Color.Black), (0, 0))
  moves += setAction(chess.api.Rook(Color.Black), (7, 0))
  moves += setAction(chess.api.Rook(Color.White), (0, 7))
  moves += setAction(chess.api.Rook(Color.White), (7, 7))
  moves += setAction(chess.api.Bishop(Color.Black), (2, 0))
  moves += setAction(chess.api.Bishop(Color.Black), (5, 0))
  moves += setAction(chess.api.Bishop(Color.White), (2, 7))
  moves += setAction(chess.api.Bishop(Color.White), (5, 7))
  moves += setAction(chess.api.Queen(Color.Black), (3, 0))
  moves += setAction(chess.api.Queen(Color.White), (3, 7))
  moves += setAction(chess.api.Pawn(Color.Black), (0, 1))
  moves += setAction(chess.api.Pawn(Color.Black), (1, 1))
  moves += setAction(chess.api.Pawn(Color.Black), (2, 1))
  moves += setAction(chess.api.Pawn(Color.Black), (3, 1))
  moves += setAction(chess.api.Pawn(Color.Black), (4, 1))
  moves += setAction(chess.api.Pawn(Color.Black), (5, 1))
  moves += setAction(chess.api.Pawn(Color.Black), (6, 1))
  moves += setAction(chess.api.Pawn(Color.Black), (7, 1))
  moves += setAction(chess.api.Pawn(Color.White), (0, 6))
  moves += setAction(chess.api.Pawn(Color.White), (1, 6))
  moves += setAction(chess.api.Pawn(Color.White), (2, 6))
  moves += setAction(chess.api.Pawn(Color.White), (3, 6))
  moves += setAction(chess.api.Pawn(Color.White), (4, 6))
  moves += setAction(chess.api.Pawn(Color.White), (5, 6))
  moves += setAction(chess.api.Pawn(Color.White), (6, 6))
  moves += setAction(chess.api.Pawn(Color.White), (7, 6))


  def setAction(piece: Piece, pos: (Int, Int)) =
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
                p => p.getActions(from, board)
              } flatMap {
                list => list find {
                  m => m.target == to
                } flatMap {
                  action => piece map { p => p.handle(board, action) }
                }
              } getOrElse board
          }
      }
      val newMove = tui.update(board)
      moves += Right(newMove)
    }
  }
}

