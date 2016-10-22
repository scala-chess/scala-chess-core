package model

import model.pieces.{King, Knight}

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
                board.get(i, j) match {
                    case None => print ("|_")
                    case Some(piece) => piece match {
                        case King(_) => print("|K")
                        case Knight(_) => print("|k")
                    }
                }
            }
            println("")
        }

        println("")
    }

}