package model

case class Field(x: Int, y: Int) {

  def getRightNeighbour = Field(x+1, y)
  def getLeftNeighbour = Field(x-1, y)
  def getUpperNeighbour = Field(x, y-1)
  def getLowerNeighbour = Field(x, y+1)
  def getUpperRightNeighbour = getRightNeighbour.getUpperNeighbour()
  def getLowerRightNeighbour = getRightNeighbour.getLowerNeighbour()
  def getUpperLeftNeighbour = getLeftNeighbour.getUpperNeighbour()
  def getLowerLeftNeighbour = getLeftNeighbour.getLowerNeighbour()

  // create a (x, y) tuple from this
  def unary_~ = Field.unapply(this).get

}
