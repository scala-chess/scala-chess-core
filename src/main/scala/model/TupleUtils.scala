package model

object TupleUtils {
  implicit class PositionTuple(val t: (Int, Int)) {
    def right = (t._1+1, t._2)
    def left = (t._1-1, t._2)
    def up = (t._1, t._2-1)
    def down = (t._1, t._2+1)
    def isSameRow(other: (Int, Int)) = t._1 == other._1
    def isSameColumn(other: (Int, Int)) = t._2 == other._2
    def straight(piece: Piece) =
      piece.color match {
          case Color.Black => t.down
          case Color.White => t.up
      }
  }
}


