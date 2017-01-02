package model.logic.modifier

import chess.api.Action
import model.{History, Pattern}
import model.logic.Logic
import model.TupleUtils._

trait Forward extends Logic {
  override def getActions(field: (Int, Int), history: History): Seq[Action] =
    history.pieceAt(field) map {
      piece =>
        super.getActions(field, history) filter {
          action =>
            val yDiffAction = diff(field.y, action.target.y)
            val yDiffPiece = diff(field.y, field.straight(piece).y)
            yDiffAction == yDiffPiece && yDiffAction != 0


        }
    } getOrElse Seq()

  def diff(a: Int, b: Int): Int = {
    val diff_ = a - b
    if (diff_ == 0) {
      0
    } else {
      diff_ / diff_.abs
    }
  }

}
