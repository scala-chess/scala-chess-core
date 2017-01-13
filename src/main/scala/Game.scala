
import chess.api._
import model.TupleUtils._
import model._
import model.logic.Logic

import scala.util.{Failure, Success, Try}

class Game {

  var history: History = History(ChessBoard())

  def getValidActions(origin: (Int, Int)): Seq[Action] = {
    if (history.getWinner.isDefined) Seq()
    else {
      history.pieceAt(origin) match {
        case Some(piece) if !history.getPieceColorOfLastAction.contains(piece.color) =>
          Pieces.getLogic(piece) flatMap {
            logic => Logic.getActions(Seq(logic), origin, history)
          }
        case _ => Seq()
      }
    }
  }

  def execAtIfValid(origin: Position, index: Int): Either[String, chess.api.ChessBoard] =
    Try(getValidActions(origin)(index)) match {
      case Success(action) =>
        history = history :+ Left(action)
        Right(toApiChessBoard)
      case Failure(f) =>
        Left("illegal action cannot be executed")
    }


  def execIfValid(action: Action): Either[String, chess.api.ChessBoard] = {
    history.positionOf(action.pieceId) flatMap {
      origin =>
        getValidActions(origin) collectFirst {
          case a: Action if a == action => a
        } map {
          validAction: Action => {
            history = history :+ Left(validAction)
            Right(toApiChessBoard)
          }
        }
    } getOrElse Left("illegal action cannot be executed")
  }

  def toApiChessBoard: chess.api.ChessBoard = {
    val boardSize = history.boardSize
    chess.api.ChessBoard(boardSize.x, boardSize.y, history.all)
  }

  def getWinner: Option[Color.Value] = history.getWinner
}

