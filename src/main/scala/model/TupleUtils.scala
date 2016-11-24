package model

import chess.api._

object TupleUtils {
  implicit class PositionTuple(val t: Position) {
    def x = t._1
    def y = t._2
    def right = (t._1+1, t._2)
    def left = (t._1-1, t._2)
    def up = (t._1, t._2-1)
    def down = (t._1, t._2+1)
    def abs = (Math.abs(t._1), Math.abs(t._2))
    def -(other: Position) = (t._1 - other._1, t._2 - other._2)
    def isSameRow(x: Int): Boolean = isSameRow((x, 0))
    def isSameRow(other: (Int, Int)) = t._1 == other._1
    def isSameColumn(other: (Int, Int)) = t._2 == other._2
    def isSameColumn(y: Int): Boolean = isSameColumn((0, y))
    def straight(piece: Piece) =
      piece.color match {
          case Color.Black => t.down
          case Color.White => t.up
      }
  }
}


