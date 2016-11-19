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
    val k1 = King(Color.Black);
    val history = List(
      PutInitial((0,0), King(Color.Black)),
      PutInitial((0,1), King(Color.Black)),
      PutInitial((0,2), k1),
      Put(0, (1,2)),
      Remove(1, (0,1))
    )

    History.pieceAt((1,2), history) must_==(Some(k1))
  }
}
