package model

abstract class Piece(val color: Color.Value) {
  type Direction = ((Int,Int)) => (Int,Int)
  override def toString: String = s"$color-${getClass.getSimpleName}"
  def isBlack = color == Color.Black
  def isWhite = !isBlack
  def isAlly(that: Piece) = this.color == that.color
  def isEnemy(that: Piece) = !isAlly(that)
  def getMoves(field: (Int, Int), board: Board): Iterable[Action]
  def handle(board: Board, action: Action): Board

  def line(dir: Direction, board: Board, pos: (Int,Int), depth: Int): List[(Int,Int)] = {
    val target = dir(pos) 
    if(depth > board.matrix.size){
      List()
    } else {
      board.get(target) match {
        case None => target :: line(dir, board, target, depth + 1)
        case Some(piece) => List(target)
      }
    }    
  }
}
