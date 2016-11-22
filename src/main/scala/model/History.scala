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
    actions.flattenToReversed(classOf[Put], classOf[Remove], classOf[PutInitial]) find {
      _.pieceId == piece.id
    } flatMap {
      case remove: Remove => None
      case putInit: PutInitial => Some(putInit.target)
      case put: Put => Some(put.target)
    }

  def all: Seq[(Position, Piece)] =
    actions.flattenTo(classOf[PutInitial]) map {
      case a: PutInitial => a.piece
    } flatMap {
      piece => positionOf(piece) map {
        pos => (pos, piece)
      }
    }

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
      boardSize => pos.x >= 0 && pos.y >= 0 && pos.x < boardSize.x && pos.y < boardSize.y
    }

  def :+(historyItem: Either[Action, Config]): History =
    new History(history :+ historyItem)

}