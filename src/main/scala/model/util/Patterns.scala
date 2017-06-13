package model.util

import chess.api.Position
import model.TupleUtils._

object Patterns {
  val diagonal = Seq(
    (t: Position) => t.up.right,
    (t: Position) => t.up.left,
    (t: Position) => t.down.right,
    (t: Position) => t.down.left
  )

  val vertical = Seq(
    (t: Position) => t.down,
    (t: Position) => t.up
  )

  val horizontal = Seq(
    (t: Position) => t.left,
    (t: Position) => t.right
  )

  val horizontalVertical = vertical ++ horizontal

  val knightPattern = Seq(
    (t: Position) => t.right.right.up,
    (t: Position) => t.right.right.down,
    (t: Position) => t.left.left.up,
    (t: Position) => t.left.left.down,
    (t: Position) => t.up.up.left,
    (t: Position) => t.up.up.right,
    (t: Position) => t.down.down.left,
    (t: Position) => t.down.down.right
  )
}