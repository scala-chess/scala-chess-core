package model.logic

import chess.api.{Action, Position}
import model.History
import model.Pieces._
import model.logic.modifier.IsOnBoard

class WherePieces(val pieceRestriction: Seq[String] = Seq.empty, val ally: Boolean = true, val enemy: Boolean = true) extends WherePiecesMixin with IsOnBoard

trait WherePiecesMixin extends Logic {
  val pieceRestriction: Seq[String]
  val ally: Boolean
  val enemy: Boolean

  override def getActions(field: Position, history: History): Seq[Action] =
    history.pieceAt(field) map {
      piece =>
        history.all filter {
          case (pos, other) => (other.isAlly(piece) && ally || other.isEnemy(piece) && enemy ) && (pieceRestriction contains  other.name)
        } map {
          case (pos, other)  => InternalDefaultAction(pos)
        }
    } getOrElse Seq()
}
