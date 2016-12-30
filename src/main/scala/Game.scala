
import chess.api._
import model.TupleUtils._
import model._
import model.logic.Logic

class Game {

  var history: History = new History(ChessBoard.init)

  def getValidActions(origin: (Int, Int)): Seq[Action] = {
    history.pieceAt(origin) match {
      case Some(piece) if !history.getPieceColorOfLastAction.contains(piece.color) =>
        Pieces.getLogic(piece) flatMap {
            logic => Logic.getActions(Seq(logic), origin, history)
          }
      case _ => Seq()
    }
  }

  def execIfValid(action: Action): Either[String, chess.api.ChessBoard] = {
    history.positionOf(action.pieceId) map {
      origin =>
        getValidActions(origin) collectFirst {
          case a: Action if a == action => a
        } map {
          validAction: Action => {
            history = history :+ Left(validAction)
            Right(toApiChessBoard)
          }
        } getOrElse Left("illegal action cannot be executed")
    } getOrElse Left("illegal action cannot be executed")
  }

  def toApiChessBoard: chess.api.ChessBoard = {
    val boardSize = history.boardSize
    chess.api.ChessBoard(boardSize.x, boardSize.y, history.all)
  }
}

