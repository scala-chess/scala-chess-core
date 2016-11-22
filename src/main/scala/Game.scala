
import chess.api._
import model.TupleUtils._
import model._
import model.logic.Logic

class Game {

  var history: History = new History(ChessBoard.init)

  def getValidActions(origin: (Int, Int)): Seq[Action] = {
    val res = history.pieceAt(origin) map {
      piece => Pieces.getLogic(piece)
    } map {
      Logic.getActions(_, origin, history)
    } getOrElse Seq()

    res
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

