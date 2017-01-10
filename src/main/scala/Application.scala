import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory

import scala.io.StdIn
import scala.util.Try

object Application {

  def main(args: Array[String]): Unit = {
    val (actorSystemName, actorName) = getActorSystemConfig
    val actorSystem = ActorSystem.create(actorSystemName)
    val controller = actorSystem.actorOf(Props[ControllerActor], actorName)

    val tui = actorSystem.actorOf(Props(new TUI(controller)))

    while (true) {
      Try(StdIn.readInt()) map (tui.tell(_, null))
    }
  }

  def getActorSystemConfig = {
    val config = ConfigFactory.load()
    val actorSystemName = config.getString("akka.chess.systemName")
    val actorName = config.getString("akka.chess.actorName")
    (actorSystemName, actorName)
  }

}
