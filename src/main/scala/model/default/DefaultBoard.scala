package model.default

import chess.api._
import model.default.DefaultPieces._
import model.util.Colors._

object DefaultBoard {
  def apply(): List[Either[Action, Config]] =
    Right(BoardSize(8, 8)) :: (actions map { action => Left(action) })

  val actions =
    List(
      PutInitial((4, 0), __KING(black)),
      PutInitial((4, 7), __KING(white)),
      PutInitial((1, 0), __KNIGHT(black)),
      PutInitial((6, 0), __KNIGHT(black)),
      PutInitial((1, 7), __KNIGHT(white)),
      PutInitial((6, 7), __KNIGHT(white)),
      PutInitial((0, 0), __ROOK(black)),
      PutInitial((7, 0), __ROOK(black)),
      PutInitial((0, 7), __ROOK(white)),
      PutInitial((7, 7), __ROOK(white)),
      PutInitial((2, 0), __BISHOP(black)),
      PutInitial((5, 0), __BISHOP(black)),
      PutInitial((2, 7), __BISHOP(white)),
      PutInitial((5, 7), __BISHOP(white)),
      PutInitial((3, 0), __QUEEN(black)),
      PutInitial((3, 7), __QUEEN(white)),
      PutInitial((0, 1), __PAWN(black)),
      PutInitial((1, 1), __PAWN(black)),
      PutInitial((2, 1), __PAWN(black)),
      PutInitial((3, 1), __PAWN(black)),
      PutInitial((4, 1), __PAWN(black)),
      PutInitial((5, 1), __PAWN(black)),
      PutInitial((6, 1), __PAWN(black)),
      PutInitial((7, 1), __PAWN(black)),
      PutInitial((0, 6), __PAWN(white)),
      PutInitial((1, 6), __PAWN(white)),
      PutInitial((2, 6), __PAWN(white)),
      PutInitial((3, 6), __PAWN(white)),
      PutInitial((4, 6), __PAWN(white)),
      PutInitial((5, 6), __PAWN(white)),
      PutInitial((6, 6), __PAWN(white)),
      PutInitial((7, 6), __PAWN(white))
    )

}