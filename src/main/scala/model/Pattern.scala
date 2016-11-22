package model

import chess.api.{Direction, Position}
import model.TupleUtils._

object Pattern {
  def line(dir: Direction, pos: Position, maxDepth: Int, depth: Int = 0): List[(Int, Int)] = {
    val target = dir(pos)
    depth match {
      case d if depth >= maxDepth => List()
      case _ => target :: line(dir, target, maxDepth, depth + 1)
    }
  }

  def direction(from: Position, to: Position): (Position) => Position = {
    val diff = (to.x - from.x, to.y - from.y)
    val absDiffs = (Math.abs(diff.x), Math.abs(diff.y))

    absDiffs match {
      case (0, 0) => (t: Position) => to
      case (x, 0) => (t: Position) => (t.x + diff.x / x, t.y)
      case (0, y) => (t: Position) => (t.x, t.y + diff.y / y)
      case (x, y) if x == y => (t: Position) => (t.x + diff.x / x, t.y + diff.y / y)
      case (x, y) => (t: Position) => to
    }
  }
}