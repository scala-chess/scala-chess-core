package model

object TupleUtils {
  implicit class PositionTuple(val t: (Int, Int)) {
    def right = (t._1+1, t._2)
    def left = (t._1-1, t._2)
    def up = (t._1, t._2-1)
    def down = (t._1, t._2+1)
    def straight(piece: Piece) =
      piece.color match {
          case Color.Black => t.down
          case Color.White => t.up
      }
  }
}


