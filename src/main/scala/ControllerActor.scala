import akka.actor.Actor

class ControllerActor extends Actor {

  override def receive = {
    case msg =>
      println(s"received message: '$msg'")
      sender() ! s"hi from scala-chess"
  }

}
