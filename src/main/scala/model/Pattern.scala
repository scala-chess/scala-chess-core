package model

import chess.api.{Direction, Position}

object Pattern {
  def line(dir: Direction, pos: Position, maxDepth: Int, depth: Int = 0): List[(Int, Int)] = {
    val target = dir(pos)
    depth match {
      case d if depth > maxDepth => List()
      case _ => target :: line(dir, target, maxDepth, depth + 1)
    }
  }
}