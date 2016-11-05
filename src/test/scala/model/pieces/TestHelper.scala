package model.pieces

object TestHelper {

  def positionsFromAsciiBoard(asciiBoard: String, posChar: String): List[(Int, Int)] =
    asciiBoard
      .split("\n")
      .map(_.trim)
      .filter(!_.isEmpty)
      .zipWithIndex
      .flatMap(
        inner =>
          inner._1
            .split('|')
            .filter(!_.isEmpty)
            .zipWithIndex
            .filter(t => posChar == t._1)
            .map(t => (t._2, inner._2))
      )
      .toList
}
