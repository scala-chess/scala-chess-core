package model

abstract class Piece(val color: Color.Value) {
  override def toString: String = s"$color-${getClass.getSimpleName}"
  def isBlack = color == Color.Black
  def isWhite = !isBlack
  def isAlly(that: Piece) = this.color == that.color
  def isEnemy(that: Piece) = !isAlly(that)
  def getMoves(field: (Int, Int)): Iterable[Move]
}
