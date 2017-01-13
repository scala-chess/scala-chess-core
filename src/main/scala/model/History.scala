package model

import chess.api._
import model.SeqExtensions._
import model.TupleUtils._

class History(val history: Seq[Either[Action, Config]] = Seq()) extends Iterable[Either[Action, Config]] {

  override def iterator: Iterator[Either[Action, Config]] = history.iterator

  def actions = history collect { case Left(action) => action }

  def config = history collect { case Right(config) => config }

  def unmoved(piece: Piece): Boolean = {
    !(actions exists {
      case x: Move => x.pieceId == piece.id
      case x: Castle => x.pieceId == piece.id
      case _ => false
    })
  }

  def pieceAt(pos: Position): Option[Piece] =
    actions.flattenToReversed(classOf[Put], classOf[Remove], classOf[PutInitial]) find {
      _.target == pos
    } flatMap {
      case _: Remove => None
      case putInit: PutInitial => Some(putInit.piece)
      case put: Put =>
        actions.flattenTo(classOf[PutInitial]).find {
          _.pieceId == put.pieceId
        } map {
          _.piece
        }
    }

  def positionOf(piece: Piece): Option[Position] =
    positionOf(piece.id)

  def positionOf(pieceId: Int): Option[Position] =
    actions.flattenToReversed(classOf[Put], classOf[Remove], classOf[PutInitial]) find {
      _.pieceId == pieceId
    } flatMap {
      case remove: Remove => None
      case putInit: PutInitial => Some(putInit.target)
      case put: Put => Some(put.target)
    }

  def all: Seq[(Position, Piece)] =
    actions.flattenTo(classOf[PutInitial]) map {
      a: PutInitial => a.piece
    } flatMap {
      piece =>
        positionOf(piece) map {
          pos => (pos, piece)
        }
    }

  def pieces: Seq[Piece] = all.map(_._2)

  def boardSize: (Int, Int) =
    config.reverse collectFirst {
      case boardSize: BoardSize => boardSize
    } map {
      boardSize => (boardSize.x, boardSize.y)
    } getOrElse(0, 0)

  def maxBoardSize: Int = {
    val size = boardSize
    Math.max(size.x, size.y)
  }

  def isOnBoard(pos: Position): Boolean =
    config collectFirst {
      case boardSize: BoardSize => boardSize
    } exists {
      boardSize => (0 until boardSize.x).contains(pos.x) && (0 until boardSize.y).contains(pos.y)
    }

  def triggerPositions(pieceType: String): Seq[Position] = {
    val size = boardSize
    val numberOfColumns = size._1
    val indexOfFirstRow = 0
    val indexOfLastRow = size._2 - 1
    val columns = 0 until numberOfColumns
    columns flatMap { columnIndex =>
      Seq((columnIndex, indexOfFirstRow), (columnIndex, indexOfLastRow))
    }
  }

  def getPieceColorOfLastAction: Option[Color.Value] = {
    val pieces = actions flattenTo (classOf[PutInitial]) map {
      _.piece
    }

    actions.lastOption match {
      case Some(p: PutInitial) => None
      case Some(a) => pieces.find { p => a.pieceId == p.id } map { p => p.color }
      case None => None
    }
  }

  def :+(historyItem: Either[Action, Config]): History =
    new History(history :+ historyItem)

  def getWinner: Option[Color.Value] =
    pieces.filter(_.name == Pieces.KING) match {
      case Seq(king) => Some(king.color)
      case _ => None
    }


}

object History {
  def apply(startConfig: Seq[Either[Action, Config]]) = new History(startConfig)
}