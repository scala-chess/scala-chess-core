import akka.actor.{ActorSystem, Props}
import akka.pattern.AskSupport
import com.typesafe.config.ConfigFactory

object Application {

  def main(args: Array[String]): Unit = {
    val (actorSystemName, actorName) = getActorSystemConfig
    val actorSystem = ActorSystem.create(actorSystemName)
    val controller = actorSystem.actorOf(Props[ControllerActor], actorName)
    actorSystem.actorOf(Props(new TUI(controller)))
  }

  def getActorSystemConfig = {
    val config = ConfigFactory.load()
    val actorSystemName = config.getString("akka.chess.systemName")
    val actorName = config.getString("akka.chess.actorName")
    (actorSystemName, actorName)
  }

}
