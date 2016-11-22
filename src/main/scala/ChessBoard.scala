import chess.api._

object ChessBoard {
  def init: List[Either[Action, Config]] =
    Right(BoardSize(8, 8)) :: (actions map { action => Left(action) })

  val actions =
    List(
      new PutInitial((4, 0), model.King(Color.Black)),
      new PutInitial((4, 0), model.King(Color.Black)),
      new PutInitial((4, 7), model.King(Color.White)),
      new PutInitial((1, 0), model.Knight(Color.Black)),
      new PutInitial((6, 0), model.Knight(Color.Black)),
      new PutInitial((1, 7), model.Knight(Color.White)),
      new PutInitial((6, 7), model.Knight(Color.White)),
      new PutInitial((0, 0), model.Rook(Color.Black)),
      new PutInitial((7, 0), model.Rook(Color.Black)),
      new PutInitial((0, 7), model.Rook(Color.White)),
      new PutInitial((7, 7), model.Rook(Color.White)),
      new PutInitial((2, 0), model.Bishop(Color.Black)),
      new PutInitial((5, 0), model.Bishop(Color.Black)),
      new PutInitial((2, 7), model.Bishop(Color.White)),
      new PutInitial((5, 7), model.Bishop(Color.White)),
      new PutInitial((3, 0), model.Queen(Color.Black)),
      new PutInitial((3, 7), model.Queen(Color.White)),
      new PutInitial((0, 1), model.Pawn(Color.Black)),
      new PutInitial((1, 1), model.Pawn(Color.Black)),
      new PutInitial((2, 1), model.Pawn(Color.Black)),
      new PutInitial((3, 1), model.Pawn(Color.Black)),
      new PutInitial((4, 1), model.Pawn(Color.Black)),
      new PutInitial((5, 1), model.Pawn(Color.Black)),
      new PutInitial((6, 1), model.Pawn(Color.Black)),
      new PutInitial((7, 1), model.Pawn(Color.Black)),
      new PutInitial((0, 6), model.Pawn(Color.White)),
      new PutInitial((1, 6), model.Pawn(Color.White)),
      new PutInitial((2, 6), model.Pawn(Color.White)),
      new PutInitial((3, 6), model.Pawn(Color.White)),
      new PutInitial((4, 6), model.Pawn(Color.White)),
      new PutInitial((5, 6), model.Pawn(Color.White)),
      new PutInitial((6, 6), model.Pawn(Color.White)),
      new PutInitial((7, 6), model.Pawn(Color.White)))
}