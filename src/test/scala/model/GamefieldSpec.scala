package model

import model.pieces.King
import org.specs2.Specification


class BoardSpec extends Specification { def is = s2"""

  A Board should
    1. be easy to create            $create
    3. get information about pieces $getPiece
    5. set a piece                  $setPiece
                          """

  val create = {
    Board()
    true must_==true
  }

  val getPiece = {
    val board = Gamefield(matrix=Board.kingGamefield)
    board.get((0, 0)) must_==Some(King(Color.Black))
    board.get((0, 1)) must_==None
    board.get((0, 2)) must_==Some(King(Color.White))

  }


  val executeValidMoves = {
    // wrong! -> only execute move if there is a piece and the piece's moves contains this move
    // how to stub "king may move"?
    val before = Gamefield(grid=Gamefield.kingGamefield)
    before.moves.isEmpty must_==true
    //val after = before.exec(SimpleMove((0, 0), (0, 1)))
    //after.moves.head must_==SimpleMove((0, 0), (0, 1))
  }

  val doNotExecuteInvalidMoves = {
    false must_==false
  }

}
