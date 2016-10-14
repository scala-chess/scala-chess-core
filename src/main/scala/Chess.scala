
object Chess {
  def main(args: Array[String]): Unit = {
    println("Hello, chess!")
  }

  def buildBoard(board: Board, actions: List[Action]): Board = {
    actions foldRight board {(b, a) => a.executeOn(board)}
  }
}
