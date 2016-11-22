
import chess.api._
import model.TupleUtils._
import model._
import model.logic.Logic

object Game {
  def main(args: Array[String]): Unit = {
    new Game().run()
  }
}

class Game {
  val tui = new TUI(this)
  var history: History = new History(ChessBoard.init)

  def getValidActions(origin: (Int, Int)): Iterable[Action] = {
    history.pieceAt(origin) map {
      piece => Pieces.getLogic(piece, 8)
    } map {
      Logic.getActions(_, origin, history)
    } getOrElse Iterable()
  }

  def execIfValid(action: Action): Either[String, chess.api.ChessBoard] = {
    action.origin map {
      origin =>
        getValidActions(origin) collectFirst {
          case a: Action if a == action => a
        } map {
          validAction: Action => {
            history :+ Left(validAction)
            Right(toApiChessBoard)
          }
        } getOrElse Left("illegal action cannot be executed")
    } getOrElse Left("illegal action cannot be executed")
  }

  def toApiChessBoard: chess.api.ChessBoard = {
    val boardSize = history.boardSize
    chess.api.ChessBoard(boardSize.x, boardSize.y, history.all)
  }

  def run() = {
    while (true) {
      val newAction = tui.update(history.all)
      history = history :+ Left(newAction)
    }
  }
}

