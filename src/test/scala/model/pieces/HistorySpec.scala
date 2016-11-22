package model.pieces

import chess.api._
import model.History
import org.specs2.Specification

/**
  * Created by andy on 18.11.2016.
  */
class HistorySpec extends Specification {
  def is =
    s2"""
        1. piece at $pieceAt
      """

  def pieceAt = {
    val k1 = model.King(Color.Black)
    val history = new History(Seq(
      PutInitial((0,0), model.King(Color.Black)),
      PutInitial((0,1), model.King(Color.Black)),
      PutInitial((0,2), k1),
      Put(0, (1,2)),
      Remove(1, (0,1))
    ) map { x => Left(x)})

    history.pieceAt((1,2)) must_==(Some(k1))
  }
}
