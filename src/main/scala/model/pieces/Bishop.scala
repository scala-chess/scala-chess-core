//package model.pieces
//
//import chess.api._
//import model.TupleUtils._
//import model._
//
//object Bishop {
//
//  def apply(color: Color.Value) = new Bishop(color, Id.next)
//
//  implicit class BishopLogic(val bishop: Bishop) extends PieceLogic(bishop) {
//    override def getActions(field: Position, board: Board, history: Iterable[Action]): Iterable[Action] =
//      Seq(
//        (t: Position) => t.up.right,
//        (t: Position) => t.up.left,
//        (t: Position) => t.down.right,
//        (t: Position) => t.down.left
//      ) flatMap {
//        dir => Pattern.line(dir, board, field)
//      } filter {
//        target => board.get(target) forall {
//          !bishop.isAlly(_)
//        }
//      } filter {
//        board.isOnBoard
//      } map {
////        target => Move(bishop.id, field, target)
//        target => Move(bishop.id, field, List())
//      }
//  }
//
//}
//
