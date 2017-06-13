package model.default

import model.default.DefaultPieces._
import model.logic._
import model.logic.modifier._
import model.util.Patterns._

object DefaultActions {
  val __action0 = new Line(directions = horizontalVertical) with TargetEmptyOrEnemy with ToMove
  val __action1 = new Line(directions = horizontalVertical ++ diagonal, maxSteps = Some(1)) with TargetEmptyOrEnemy with ToMove
  val __action2 = new WherePieces(pieceRestriction = Seq(__ROOK.name), enemy = false) with Unmoved with TargetUnmoved with OnSameRow with EmptyBetween with ToCastle
  val __action3 = new Line(directions = horizontalVertical ++ diagonal) with TargetEmptyOrEnemy with ToMove
  val __action4 = new Line(directions = diagonal) with TargetEmptyOrEnemy with ToMove
  val __action5 = new Step(directions = vertical) with TargetEmpty with Forward with ToMoveOrReplace
  val __action6 = new Step(directions = vertical, Some(2)) with TargetEmpty with Forward with Unmoved with ToMoveOrReplace
  val __action7 = new Step(directions = diagonal) with TargetEnemy with Forward with ToMoveOrReplace
  val __action8 = new Step(directions = knightPattern) with TargetEmptyOrEnemy with ToMove

}
