package model

import chess.api._
import model.default.DefaultPieces._
import org.specs2.Specification

class HistorySpec extends Specification {
  def is =
    s2"""
        1. piece at $pieceAt
      """

  def pieceAt = {
    val k1 = __KING(Color.Black)
    val history = new History(Seq(
      PutInitial((0,0), __KING(Color.Black)),
      PutInitial((0,1), __KING(Color.Black)),
      PutInitial((0,2), k1),
      Put(0, (1,2)),
      Remove(1, (0,1))
    ) map { x => Left(x)})

    history.pieceAt((1,2)) must_==(Some(k1))
  }
}
