class Position(val x: Int,val y: Int){

  def +(that: Position) =
    new Position (this.x + that.x, this.y + that.y)

  def -(that: Position) =
    new Position (this.x - that.x, this.y - that.y)

}

object Position{
  def fromTuple(tuple:(Int,Int)): Position =
    new Position(tuple._1, tuple._2)

}