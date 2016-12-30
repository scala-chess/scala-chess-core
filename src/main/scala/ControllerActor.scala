import akka.actor.{Actor, ActorRef}
import chess.api._
import chess.api.actors.{RegisterObserver, UnregisterObserver}

class ControllerActor extends Actor {

  val observers = scala.collection.mutable.SortedSet.empty[ActorRef]
  val game = new Game()

  override def receive = {
    case RegisterObserver => observers += sender(); sender() ! Update(game.toApiChessBoard, game.getWinner)
    case UnregisterObserver => observers -= sender()
    case QueryValidActions(pos: Position) => sender() ! game.getValidActions(pos)
    case action: Action => game.execIfValid(action) match {
      case Right(chessBoard) => observers.foreach(_ ! Update(chessBoard, game.getWinner))
      case Left(error) => sender() ! InvalidAction(error, action)
    }
  }

}
