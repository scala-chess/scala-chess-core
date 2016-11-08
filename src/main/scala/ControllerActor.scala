import akka.actor.{Actor, ActorRef}
import chess.api.{Action, InvalidAction, Update}
import chess.api.actors.{RegisterObserver, UnregisterObserver}

class ControllerActor extends Actor {

  val observers = scala.collection.mutable.SortedSet.empty[ActorRef]
  val game = new Game()

  override def receive = {
    case RegisterObserver(obs: ActorRef) => observers += obs; obs ! Update(game.toApiChessBoard)
    case UnregisterObserver(obs: ActorRef) => observers -= obs
    case action: Action => game.execIfValid(action) match {
      case Right(chessBoard) => observers.foreach(_ ! Update(chessBoard))
      case Left(error) => sender() ! InvalidAction(error, action)
    }
  }

}
