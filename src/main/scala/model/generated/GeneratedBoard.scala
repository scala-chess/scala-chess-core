package model.generated

import chess.api._
import model.generated.GeneratedPieces._
import model.util.Colors._

object GeneratedBoard {
  def apply(): List[Either[Action, Config]] =
    Right(BoardSize(5, 5)) :: (actions map { action => Left(action) })

  val actions =
    List(
    PutInitial((0,0), __PAWN(black)),
    PutInitial((1,0), __ROOK(black)),
    PutInitial((2,0), __PAWN(black)),
    PutInitial((3,0), __BISHOP(black)),
    PutInitial((4,0), __PAWN(black)),
    PutInitial((0,4), __PAWN(white)),
    PutInitial((1,4), __BISHOP(white)),
    PutInitial((2,4), __PAWN(white)),
    PutInitial((3,4), __ROOK(white)),
    PutInitial((4,4), __PAWN(white))
    )

}