package model

import types.Direction

object Pattern {
  def line(dir: Direction, board: Board, pos: (Int, Int), depth: Int = 0): List[(Int, Int)] = {
    val target = dir(pos)
    if (depth > board.matrix.size) {
      List()
    } else {
      board.get(target) match {
        case None => target :: line(dir, board, target, depth + 1)
        case Some(piece) => List(target)
      }
    }
  }
}