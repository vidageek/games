package vggames.scala.actors

import akka.actor.{Actor, ActorLogging, ActorRef}
import akka.util.duration._
import scala.collection.mutable.HashMap
import vggames.scala.tasks.judge.ExecutionFailure

class ScalaProcessorActor extends Actor with ActorLogging {
  private[this] val runningThreads = new HashMap[Int, CodeThread[_]]
  
  def receive = {
    case Run(code) => 
      val ct = new CodeThread(sender, code)
      runningThreads += ct.hashCode -> ct
      ct.start
      context.system.scheduler.scheduleOnce(3 seconds) {
        self ! CodeRunTimeout(ct.hashCode)
      }
      
    case CodeRunTimeout(hash) =>
      val ct = runningThreads(hash)
      if (ct.isAlive) ct.interrupt
      runningThreads -= hash
      
    case msg => log.warning("received unknown message: {}", msg)
  }
  
  case class CodeThread[V](replyTo: ActorRef, code: () => V) extends Thread {
    override def run() {
      try {
        replyTo ! code()
      } catch {
        case e: InterruptedException => replyTo ! ExecutionFailure(new IllegalStateException("Exceeded max compilation and run time."))
        case e => replyTo ! ExecutionFailure(e)
      }
    }
  }
}
