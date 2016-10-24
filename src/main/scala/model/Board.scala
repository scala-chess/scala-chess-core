package model

class Board {
  var matrix: Vector[Vector[Option[Piece]]] = Board.defaultChessMatrix

  def get(pos: (Int, Int)): Option[Piece] = {
    if (!inMatrix(pos)) {
      None
    } else {
      matrix(pos._1)(pos._2)
    }
  }

  //def getAll(): Iterable[Piece] =
  //    matrix.flatten or something

  def set(pos: (Int, Int), piece: Option[Piece]): Board =
    if (inMatrix(pos)) {
      val inner = this.matrix(pos._1)
      val innerUpdated = inner.updated(pos._2, piece)
      val matrix = this.matrix.updated(pos._1, innerUpdated)
      val newBoard = new Board()
      newBoard.matrix = matrix
      newBoard
    } else {
      this
    }

  def inMatrix(pos: (Int, Int)): Boolean =
    matrix.indices.contains(pos._1) && matrix(0).indices.contains(pos._2)
}

object Board {
  val defaultMatrixSize = 8

  // 8x8
  def defaultChessMatrix = Vector.fill(defaultMatrixSize)(Vector.fill(defaultMatrixSize)(None))
}