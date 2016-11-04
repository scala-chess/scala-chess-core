package model

import chess.api._

case class Board(matrix: Vector[Vector[Option[Piece]]] = Board.defaultChessMatrix) {
  require(matrix.nonEmpty)
  require(matrix.head.nonEmpty)

  def get(pos: (Int, Int)): Option[Piece] =
    matrix.lift(pos._1) flatMap { vec => vec.lift(pos._2).flatten }

  def getAll: Iterable[((Int, Int), Piece)] = {
    matrix.zipWithIndex flatMap {
      case (vector, indexX) =>
        vector.zipWithIndex collect {
          case (Some(piece), indexY) => ((indexX, indexY), piece)
        }
    }
  }

  def isOnBoard(pos: (Int, Int)): Boolean =
    matrix.indices.contains(pos._1) && matrix(0).indices.contains(pos._2)

  def set(pos: (Int, Int), piece: Option[Piece]): Board = {
    matrix.lift(pos._1) flatMap {
      _.lift(pos._2)
    } map {
      _ => Board(matrix.updated(pos._1, matrix(pos._1).updated(pos._2, piece)))
    } getOrElse this
  }
}

object Board {

  // 8x8
  val defaultMatrixSize = 8

  def empty(int: Size = defaultMatrixSize) = Vector.fill(defaultMatrixSize)(Vector.fill(defaultMatrixSize)(None)) 

  def defaultChessMatrix = Vector.fill(defaultMatrixSize)(Vector.fill(defaultMatrixSize)(None))

  def kingGamefield = Vector(Vector(Some(King(Color.Black, 0)), None, Some(King(Color.White, 1))))
}