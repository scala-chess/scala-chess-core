package model

class Board {
    var matrix: Vector[Vector[Option[Piece]]] = Vector(
                        Vector(None,None,None,None,None,None,None,None),
                        Vector(None,None,None,None,None,None,None,None),
                        Vector(None,None,None,None,None,None,None,None),
                        Vector(None,None,None,None,None,None,None,None),
                        Vector(None,None,None,None,None,None,None,None),
                        Vector(None,None,None,None,None,None,None,None),
                        Vector(None,None,None,None,None,None,None,None),
                        Vector(None,None,None,None,None,None,None,None)
                    )

    def get(pos: (Int, Int)): Option[Piece] =
        if(!inMatrix(pos)){
            None
        } else {
            matrix(pos._1)(pos._2)
        }

    //def getAll(): Iterable[Piece] =
    //    matrix.flatten or something

    def set(pos: (Int, Int), piece: Option[Piece]):Board =
        if(inMatrix(pos)){
            val inner = this.matrix(pos._1)
            val innerUpdated = inner.updated(pos._2, piece)
            val matrix = this.matrix.updated(pos._1, innerUpdated)
            val newBoard = new Board();
            newBoard.matrix = matrix;
            return newBoard;
        } else {
          this
        }
    
    def inMatrix(pos: (Int,Int)): Boolean =
        0 <= pos._1 && pos._1 <= 7 && 0 <= pos._2 && pos._2 <= 7

}