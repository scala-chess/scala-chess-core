import chess.api._
import model.Pieces._

object ChessBoard {
  def init: List[Either[Action, Config]] =
    Right(BoardSize(8, 8)) :: (actions map { action => Left(action) })

  val actions =
    List(
      PutInitial((4, 0), king(black)),
      PutInitial((4, 7), king(white)),
      PutInitial((1, 0), knight(black)),
      PutInitial((6, 0), knight(black)),
      PutInitial((1, 7), knight(white)),
      PutInitial((6, 7), knight(white)),
      PutInitial((0, 0), rook(black)),
      PutInitial((7, 0), rook(black)),
      PutInitial((0, 7), rook(white)),
      PutInitial((7, 7), rook(white)),
      PutInitial((2, 0), bishop(black)),
      PutInitial((5, 0), bishop(black)),
      PutInitial((2, 7), bishop(white)),
      PutInitial((5, 7), bishop(white)),
      PutInitial((3, 0), queen(black)),
      PutInitial((3, 7), queen(white)),
      PutInitial((0, 1), pawn(black)),
      PutInitial((1, 1), pawn(black)),
      PutInitial((2, 1), pawn(black)),
      PutInitial((3, 1), pawn(black)),
      PutInitial((4, 1), pawn(black)),
      PutInitial((5, 1), pawn(black)),
      PutInitial((6, 1), pawn(black)),
      PutInitial((7, 1), pawn(black)),
      PutInitial((0, 6), pawn(white)),
      PutInitial((1, 6), pawn(white)),
      PutInitial((2, 6), pawn(white)),
      PutInitial((3, 6), pawn(white)),
      PutInitial((4, 6), pawn(white)),
      PutInitial((5, 6), pawn(white)),
      PutInitial((6, 6), pawn(white)),
      PutInitial((7, 6), pawn(white)))
}