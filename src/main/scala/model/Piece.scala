package model

abstract class Piece(val color: Color.Value) {
  override def toString: String = s"$color-${getClass.getSimpleName}"
  def isAlly(that: Piece) = this.color.equals(that.color)
  def isEnemy(that: Piece) = !isAlly(that)
}
