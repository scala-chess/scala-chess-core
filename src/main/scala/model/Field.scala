package model

case class Field(x: Int, y: Int) {

  def getRightNeighbour = Field(x+1, y)
  def getLeftNeighbour = Field(x-1, y)
  def getUpperNeighbour = Field(x, y-1)
  def getLowerNeighbour = Field(x, y+1)
  def getUpperRightNeighbour = getUpperNeighbour.getRightNeighbour
  def getLowerRightNeighbour = getLowerNeighbour.getRightNeighbour
  def getUpperLeftNeighbour = getUpperNeighbour.getLeftNeighbour
  def getLowerLeftNeighbour = getLowerNeighbour.getLeftNeighbour

  // create a (x, y) tuple from this
  def unary_~ = Field.unapply(this).get

}
