package vggames.scala.code

import com.twitter.util.{ Eval => TwitterEval }
import java.security.Permission
import vggames.scala.specs.GameSpecification
import vggames.scala.tasks.judge.ExecutionFailure
import vggames.shared.task.JudgedTask
import vggames.scala.code.Wrappers._
import java.io.File
import com.twitter.util.Eval.CompilerException
import scala.collection.mutable.SynchronizedQueue
import akka.actor.ActorSystem
import akka.actor.Actor
import akka.actor.Props
import org.apache.log4j.Logger
import vggames.shared.Hash

class ScalaProcessor[T <: CodeRestrictions[_]](spec : GameSpecification[T]) {
  val className = "ExpressionRunner"
  val fullName = "scalagameunsafe.ExpressionRunner"

  def processCode(code : String) : JudgedTask = {

    val klass = compile(code)
    run(className, klass, code)
  }

  def run(className : String, klass : Class[_], submittedCode : String) : JudgedTask = {
    val code = klass.newInstance.asInstanceOf[T]
    try {
      spec.run(code, submittedCode).judgement
    } catch {
      case t : Exception => new ExecutionFailure(t)
    }
  }

  private def compile(code : String) = {
    if (code.contains("finally") || code.contains("catch") ||
      code.contains(TaskRunSecurityManager.getClass.getSimpleName.replace("$", "")))
      throw new SecurityException("Tentativa de executar c&oacute;digo privilegiado dentro de uma task.")

    val wrapped = wrap(className, code, spec)
    val codeHash = Hash(wrapped)
    val toCompile = wrapped.replace(className, className + codeHash)

    Compile(_.toClass(toCompile, fullName + codeHash))
  }
}

object Compile {
  val pool = new Pool

  def compiler() = pool.pop.getOrElse(new Eval(None))

  def reuse(eval : Eval) = pool += eval

  def apply(f : Eval => Class[_]) = {
    val eval = compiler()
    var shouldReuse = true
    try {
      f(eval)
    } catch {
      case t : CompilerException => { shouldReuse = false; throw t }
      case t : Exception => throw t
    } finally {
      if (shouldReuse)
        reuse(eval)
    }
  }

  import scala.concurrent.duration._

  class Warmer extends Actor {

    val log = Logger.getLogger(classOf[Warmer])

    def receive = {
      case Check => {
        if (pool.size < 10) {
          log.info("Warming up Eval")
          val eval = new Eval(None)
          eval("1 + 1")
          pool += eval
        }
      }
    }
  }

  val system = ActorSystem("Compiler")

  case object Check

  import system.dispatcher
  system.scheduler.schedule(0.milliseconds, 5.seconds, system.actorOf(Props[Warmer], "Warmer"), Check)

}

class Pool {

  val queue = new SynchronizedQueue[Eval]

  def +=(eval : Eval) = queue.enqueue(eval)

  def pop : Option[Eval] = {
    try {
      Some(queue.dequeue)
    } catch {
      case t : Exception => None
    }
  }

  def size = queue.size
}

class Eval(file : Option[File]) extends TwitterEval(file) {

  def toClass(code : String, name : String) = {
    compile(code)
    findClass(name)
  }
}

object TaskRunSecurityManager extends SecurityManager {
  val unsafe = new ThreadLocal[Boolean]

  override def checkPermission(perm : Permission) = handlePermission(perm)
  override def checkPermission(perm : Permission, context : Object) = handlePermission(perm)

  def handlePermission(perm : Permission) =
    if (unsafe.get) throw new SecurityException("Tentativa de executar c&oacute;digo privilegiado dentro de uma task.")
}

object Wrappers {
  def wrap(className : String, code : String, spec : GameSpecification[_]) = {
    "package scalagameunsafe\n" +
      "import vggames.scala.code._\n" +
      "class " + className + " extends " + spec.extendsType + " {\n" +
      "  def run" + spec.runSignature + " = {\n" +
      spec.beforeCode + "\n" +
      code + "\n" +
      spec.afterCode + "\n" +
      "  }\n" +
      "}\n"
  }
}
