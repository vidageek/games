package vggames.scala.actors

import akka.actor.{ Actor, ActorLogging, ActorRef }
import scala.concurrent.duration._
import vggames.scala.tasks.judge.ExecutionFailure

class ScalaProcessor extends Actor with ActorLogging {
  import ScalaProcessor._

  private[this] var runningThreads = Map[Int, CodeThread[_]]()

  def receive = {
    case Run(code) =>
      val ct = new CodeThread(sender, code)
      runningThreads += ct.hashCode -> ct
      ct.start
      import context.dispatcher
      context.system.scheduler.scheduleOnce(2 seconds) {
        self ! CodeRunTimeout(ct.hashCode)
      }

    case CodeRunTimeout(hash) =>
      val ct = runningThreads(hash)
      if (ct.isAlive) ct.stop
      runningThreads -= hash

    case msg => log.warning("received unknown message: {}", msg)
  }

  case class CodeThread[V](replyTo: ActorRef, code: () => V) extends Thread {
    override def run() {
      try {
        replyTo ! code()
      } catch {
        case e: ThreadDeath =>
          replyTo ! ExecutionFailure(new IllegalStateException("Exceeded max compilation and run time."))
          throw e // read Thread.stop javadoc to understand why this is necessary

        case e: Exception => replyTo ! ExecutionFailure(e)
      }
    }
  }
}

object ScalaProcessor {
  case class Run[V](code: () => V)
  case class CodeRunTimeout(hash: Int)
}
