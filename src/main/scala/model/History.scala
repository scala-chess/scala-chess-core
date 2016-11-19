package model

import chess.api._
import model.ListExtensions._

class History extends Iterable[Action] {
  val actions: List[Action] = List()

  def unmoved(history: Iterable[Action], piece: Piece): Boolean = {
    !(history exists {
      case x: Move => x.pieceId == piece.id
      case x: Castle => x.pieceId == piece.id
      case _ => false
    })
  }

  def pieceAt(pos: Position): Option[Piece] =
    actions.reverse.flattenTo(classOf[Put], classOf[Remove], classOf[PutInitial]) find {
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

  override def iterator: Iterator[Action] = actions.iterator
}