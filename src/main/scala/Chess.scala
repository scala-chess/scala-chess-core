import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory

object Chess {

  def main(args: Array[String]): Unit = {
    val (actorSystemName, actorName) = getActorSystemConfig
    val actorSystem = ActorSystem.create(actorSystemName)
    val chessController = actorSystem.actorOf(Props[ControllerActor], actorName)
  }

  def getActorSystemConfig = {
    val config = ConfigFactory.load()
    val actorSystemName = config.getString("akka.chess.systemName")
    val actorName = config.getString("akka.chess.actorName")
    (actorSystemName, actorName)
  }

}
