//package model.pieces
//
//import chess.api._
//import model.Board
//import model.pieces.Rook.RookLogic
//import org.specs2._
//
//class RookSpec extends Specification {
//  def is =
//    s2"""
//
//  A Piece should
//    1. should move to empty fields in horizontal or vertical direction                $moveToEmpty
//    2. should not move to fields with pieces of the same color or behind them         $notMoveSameColor
//
//                          """
//
//  val emptyBoard = new Board(Board.empty(5))
//  val whiteRook = model.pieces.Rook(Color.White)
//
//  val movesToEmptyBoard = """
//      |_|_|_|x|_|
//      |_|_|_|x|_|
//      |_|_|_|x|_|
//      |x|x|x|R|x|
//      |_|_|_|x|_|
//    """
//
//
//  def moveToEmpty = {
//    val rookPos = TestHelper.positionsFromAsciiBoard(movesToEmptyBoard, "R").head
//    val boardWithRook = emptyBoard.set(rookPos, Some(whiteRook))
//    val actions = new RookLogic(whiteRook).getActions(rookPos, boardWithRook, Iterable())
//    val expectedActions = TestHelper.positionsFromAsciiBoard(movesToEmptyBoard, "x").map { target => Move(whiteRook.id, rookPos, target)}
//    actions must containTheSameElementsAs(expectedActions)
//  }
//
//  val notMoveSameColorBoard = """
//                            |_|_|_|_|_|
//                            |_|_|_|_|_|
//                            |_|_|_|P|_|
//                            |_|P|x|R|x|
//                            |_|_|_|x|_|
//                          """
//
//  def notMoveSameColor = {
//    val rookPos = TestHelper.positionsFromAsciiBoard(notMoveSameColorBoard, "R").head
//    val boardWithRook = emptyBoard.set(rookPos, Some(whiteRook))
//    val boardWithRookAndPawns = TestHelper.positionsFromAsciiBoard(notMoveSameColorBoard, "P").foldLeft(boardWithRook) {
//      (board, piecePos) => board.set(piecePos, Some(model.pieces.Pawn(Color.White)))
//    }
//    val actions = new RookLogic(whiteRook).getActions(rookPos, boardWithRookAndPawns, Iterable())
//    val expectedActions = TestHelper.positionsFromAsciiBoard(notMoveSameColorBoard, "x").map { target => Move(whiteRook.id, rookPos, target)}
//    actions must containTheSameElementsAs(expectedActions)
//  }
//}
