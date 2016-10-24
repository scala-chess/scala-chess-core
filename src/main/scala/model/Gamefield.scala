package model

import model.pieces.King


case class Gamefield(grid: Vector[Vector[Option[Piece]]] = Gamefield.emptyGrid, moves: Seq[Move]=Seq()) {
  require(grid.nonEmpty)
  require(grid(0).nonEmpty)

  def validIndices(tuple: (Int, Int)): Boolean = (grid.indices contains tuple._1) && (grid(0).indices contains tuple._2)
  def getPiece(field: (Int, Int)): Option[Piece] = grid(field._1)(field._2)
  def hasPiece(field: (Int, Int)): Boolean = getPiece(field).isDefined
  def exec(move: Move) =  copy(moves = this.moves :+ move)
  def getGrid(): Unit = {}
}

object Gamefield {

  val defaultGridSize = 8
  def emptyGrid = Vector.fill(defaultGridSize)(Vector.fill(defaultGridSize)(None))
  def kingGamefield = Vector(Vector(Some(King(Color.Black)), None, Some(King(Color.White))))

}