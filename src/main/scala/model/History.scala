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
      case remove: Remove => None
      case putInit: PutInitial => Some(putInit.piece)
      case put: Put =>
        actions.find {
          case a: PutInitial => a.pieceId == put.pieceId
          case _ => false
        } map {
          case a: PutInitial => a.piece
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

  //  TODO add config to set
  def triggerPositions(pieceId: Int): Seq[Position] = Seq()

  def getPieceColorOfLastAction: Option[Color.Value] =
    actions flatMap {
      case _: PutInitial => None
      case other => Some(other)
    } lastOption match {
      case Some(action) => pieceAt(action.target) match {
        case Some(piece) => Some(piece.color)
        case _ => None
      }
      case _ => None
    }

  def :+(historyItem: Either[Action, Config]): History =
    new History(history :+ historyItem)

  def getWinner: Option[Color.Value] = {
    pieces.filter(_.name == Pieces.KING) match {
      case Seq(king) => Some(king.color)
      case _ => None
    }
  }

}