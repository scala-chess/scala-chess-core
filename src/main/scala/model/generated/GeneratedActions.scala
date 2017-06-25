package model.generated

import model.generated.GeneratedPieces._
import model.logic._
import model.logic.modifier._
import model.util.Patterns._

object GeneratedActions {
  val __action0 = new Line(directions = diagonal, maxSteps = Some(2)) with TargetEmptyOrEnemy with ToMove
  val __action1 = new WherePieces(pieceRestriction = Seq("Pawn"), enemy = false) with ToMove
  val __action2 = new Line(directions = vertical, maxSteps = Some(3)) with TargetEmptyOrEnemy with ToMove
  val __action3 = new Line(directions = diagonal ++ vertical ++ horizontal, stopOnCollision = false) with TargetEmpty with ToMove
}