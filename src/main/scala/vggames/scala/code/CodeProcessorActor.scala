package vggames.scala.code

import akka.actor.Actor
import akka.actor.ActorLogging

class CodeProcessorActor extends Actor with ActorLogging {
  def receive = {
    case msg => log.warning("received some unknown message: {}", msg)
  }
}

case class Compile(code : String)
case class CompilationResult(ok : Boolean)
