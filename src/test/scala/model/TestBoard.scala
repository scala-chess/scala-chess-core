package model

import chess.api._
import model.Pieces._

object TestBoard {
  def apply(): List[Either[Action, Config]] =
    Right(BoardSize(8, 8)) :: (actions map { action => Left(action) })

  val actions =
    List(
      PutInitial((5, 3), rook(black)),
      PutInitial((1, 3), pawn(white)),
      PutInitial((5, 6), pawn(white))
    )
}