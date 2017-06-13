package model.generated

import chess.api._
import model.generated.GeneratedPieces._
import model.util.Colors._

object GeneratedBoard {
  def apply(): List[Either[Action, Config]] =
    Right(BoardSize(3, 3)) :: (actions map { action => Left(action) })

  val actions =
    List(
      PutInitial((0,0), __BISHOP(black)),
      PutInitial((0,2), __BISHOP(white))
    )

}