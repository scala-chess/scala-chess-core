import chess.api._

object ChessBoard {
  def init: List[Either[Action, Config]] =
    Right(BoardSize(8, 8)) :: (actions map { action => Left(action) })

  val actions =
    List(
      PutInitial((4, 0), model.King(Color.Black)),
      PutInitial((4, 0), model.King(Color.Black)),
      PutInitial((4, 7), model.King(Color.White)),
      PutInitial((1, 0), model.Knight(Color.Black)),
      PutInitial((6, 0), model.Knight(Color.Black)),
      PutInitial((1, 7), model.Knight(Color.White)),
      PutInitial((6, 7), model.Knight(Color.White)),
      PutInitial((0, 0), model.Rook(Color.Black)),
      PutInitial((7, 0), model.Rook(Color.Black)),
      PutInitial((0, 7), model.Rook(Color.White)),
      PutInitial((7, 7), model.Rook(Color.White)),
      PutInitial((2, 0), model.Bishop(Color.Black)),
      PutInitial((5, 0), model.Bishop(Color.Black)),
      PutInitial((2, 7), model.Bishop(Color.White)),
      PutInitial((5, 7), model.Bishop(Color.White)),
      PutInitial((3, 0), model.Queen(Color.Black)),
      PutInitial((3, 7), model.Queen(Color.White)),
      PutInitial((0, 1), model.Pawn(Color.Black)),
      PutInitial((1, 1), model.Pawn(Color.Black)),
      PutInitial((2, 1), model.Pawn(Color.Black)),
      PutInitial((3, 1), model.Pawn(Color.Black)),
      PutInitial((4, 1), model.Pawn(Color.Black)),
      PutInitial((5, 1), model.Pawn(Color.Black)),
      PutInitial((6, 1), model.Pawn(Color.Black)),
      PutInitial((7, 1), model.Pawn(Color.Black)),
      PutInitial((0, 6), model.Pawn(Color.White)),
      PutInitial((1, 6), model.Pawn(Color.White)),
      PutInitial((2, 6), model.Pawn(Color.White)),
      PutInitial((3, 6), model.Pawn(Color.White)),
      PutInitial((4, 6), model.Pawn(Color.White)),
      PutInitial((5, 6), model.Pawn(Color.White)),
      PutInitial((6, 6), model.Pawn(Color.White)),
      PutInitial((7, 6), model.Pawn(Color.White)))
}