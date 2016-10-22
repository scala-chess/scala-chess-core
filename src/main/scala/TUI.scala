package model

import model.pieces.{King, Knight, Rook, Bishop}

import scala.io.StdIn

class TUI {

    def update(board: Board): ((Int,Int),(Int,Int)) = {
        printBoard(board)
        val xStart = StdIn.readInt()
        val yStart = StdIn.readInt()
        val xEnd = StdIn.readInt()
        val yEnd = StdIn.readInt()
        ((xStart,yStart), (xEnd,yEnd))
        
    }


    def printBoard(board: Board) = {
        var i = 0
        var j = 0

        for(i <- 0 to 7){
            for(j <- 0 to 7){
                val letter = board.get(j, i) match {
                    case None => "_"
                    case Some(piece) => piece match {
                        case King(_) => inColor(piece, "K")
                        case Knight(_) => inColor(piece, "k")
                        case Rook(_) => inColor(piece, "R")
                        case Bishop(_) => inColor(piece, "B")
                    }
                }
                print("|" + letter)
            }
            println("")
        }

        println("")
    }

    def inColor(piece: Piece, letter: String): String =
        piece.color match {
            case Color.Black => Console.BLUE + letter + Console.BLACK
            case Color.White => Console.RED + letter + Console.BLACK
        }

}