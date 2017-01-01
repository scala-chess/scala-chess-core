import akka.actor.{Actor, ActorRef}
import chess.api._
import chess.api.actors.{RegisterObserver, UnregisterObserver}

class ControllerActor extends Actor {

  val observers = scala.collection.mutable.SortedSet.empty[ActorRef]
  val game = new Game()

  override def receive = {
    case RegisterObserver => observers += sender(); sender() ! Update(game.toApiChessBoard, game.getWinner)
    case UnregisterObserver => observers -= sender()
    case QueryValidActions(origin: Position) => sender() ! game.getValidActions(origin)
    case ExecuteAction(position: Position, index: Int) => afterExec(game.execAtIfValid(position, index))
    case action: Action => afterExec(game.execIfValid(action))
  }

  def afterExec(result: Either[String, chess.api.ChessBoard]) =
  result match {
    case Right(chessBoard) => observers.foreach(_ ! Update(chessBoard))
    case Left(error) => sender() ! InvalidAction(error)

  }

}
