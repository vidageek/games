package vggames.scala.actors

import akka.actor.{ Actor, ActorSystem, Props }
import akka.routing.RoundRobinRouter

class GameMaster extends Actor {
  private val scalaProcessorRouter = GameMaster.system.actorOf(
    Props[ScalaProcessor].withRouter(RoundRobinRouter(nrOfInstances = 5)), "scala-processor-router")

  def receive = {
    case r: Run[_] => scalaProcessorRouter.!(r)(sender)
  }
}

object GameMaster {
  val system = ActorSystem("vg-games-system")
  val master = system.actorOf(Props[GameMaster], "game-master")
}
