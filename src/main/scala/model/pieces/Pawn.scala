package model.pieces

import chess.api._
import model.TupleUtils._
import model._

object Pawn {

  def apply(color: Color.Value) = new Pawn(color, Id.next)

  implicit class PawnLogic(val pawn: Pawn) extends PieceLogic(pawn) {
    override def getActions(field: (Int, Int), board: Board, history: Iterable[Action]): Iterable[Action] = {

      val toEmpty2Targets = Seq(
        field.straight(pawn).straight(pawn)
      ) filter {
        target =>
          board.get(field.straight(pawn)).isEmpty &&
            board.get(target).isEmpty &&
            History.unmoved(history, pawn)
      }

      val toEmptyTargets = Seq(
        field.straight(pawn)
      ) filter {
        target => board.get(target).isEmpty
      }

      val toOccupiedTargets = Seq(
        field.straight(pawn).left,
        field.straight(pawn).right
      ) filter {
        target => board.get(target) exists {
          !pawn.isAlly(_)
        }
      }

      val emptyOccupiedActions =
        toEmptyTargets ++ toOccupiedTargets ++ toEmpty2Targets filter {
          board.isOnBoard
        } flatMap {
          case target@(x, y) if Seq(board.matrix.size - 1, 0).contains(y) =>
            Seq(
              MoveAndChangeChoice.Bishop,
              MoveAndChangeChoice.Queen,
              MoveAndChangeChoice.Knight,
              MoveAndChangeChoice.Rook
            ) map {
              MoveAndChange(pawn.id, field, List(), _)
            }
          case target@(x, y) => Seq(Move(pawn.id, field, List()))
        }

      emptyOccupiedActions
    }
  }

}