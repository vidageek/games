package vggames.shared.log

import vggames.shared.task.Task
import vggames.shared.task.JudgedTask
import akka.actor.Actor
import br.com.caelum.vraptor.ioc.Component
import akka.actor.ActorSystem
import akka.actor.Props

@Component
class Log {
  val logActor = ActorSystem("GameLog").actorOf(Props[GameLog], "GameLogActor")
  def log(item : LogItem) : Unit = logActor ! item
}

class GameLog extends Actor {
  def receive = {
    case item : LogItem => item.log
  }
}

trait LogItem {
  def log
}
