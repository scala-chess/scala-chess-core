package model

import model.pieces.King
import org.specs2.Specification


class GamefieldSpec extends Specification { def is = s2"""

  A Gamefield should
    1. be easy to create            $create
    2. check indices                $indices
    3. get information about pieces $getPiece
    4. tell you if there is a piece $hasPiece
    5. execute valid moves          $executeValidMoves
    6. do not execute invalid moves $doNotExecuteInvalidMoves
                          """

  val create = {
    Gamefield()
    true must_==true
  }

  val indices = {
    val gamefield = Gamefield()
    gamefield.validIndices((0,0)) must_==true
    gamefield.validIndices((7,7)) must_==true
    gamefield.validIndices((8,0)) must_==false
    gamefield.validIndices((0,8)) must_==false
    gamefield.validIndices((8,8)) must_==false
  }

  val getPiece = {
    val kingGamefield = Gamefield(grid=Gamefield.kingGamefield)
    kingGamefield.getPiece((0, 0)) must_==Some(King(Color.Black))
    kingGamefield.getPiece((0, 1)) must_==None
    kingGamefield.getPiece((0, 2)) must_==Some(King(Color.White))

  }

  val hasPiece = {
    val kingGamefield = Gamefield(grid=Gamefield.kingGamefield)
    kingGamefield.hasPiece((0, 0)) must_==true
    kingGamefield.hasPiece((0, 1)) must_==false
    kingGamefield.hasPiece((0, 2)) must_==true
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
