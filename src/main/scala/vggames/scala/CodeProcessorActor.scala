package vggames.scala

import akka.actor.actorRef2Scala
import akka.actor.{ActorLogging, Actor}

class CodeProcessorActor extends Actor with ActorLogging {
  def receive = {
    case Compile(code) => sender ! CompilationResult(new ScalaProcessor().processCode(code))
    case msg => log.warning("received some unknown message: {}", msg)
  }
}

case class Compile(code: String)
case class CompilationResult(ok: Boolean)
