package model

import chess.api._
import model.default.DefaultPieces._
import model.util.Colors._


object TestBoard {
  def apply(): List[Either[Action, Config]] =
    Right(BoardSize(8, 8)) :: (actions map { action => Left(action) })

  val actions =
    List(
      PutInitial((5, 3), __ROOK(black)),
      PutInitial((1, 3), __PAWN(white)),
      PutInitial((5, 6), __PAWN(white))
    )
}