package vggames.scala.code

import ScalaProcessor.executor
import com.twitter.util.Eval
import java.security.Permission
import java.util.concurrent.SynchronousQueue
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import vggames.scala.specs.GameSpecification
import vggames.scala.tasks.judge.ExecutionFailure
import vggames.shared.task.JudgedTask
import vggames.scala.code.Wrappers._
import vggames.scala.tasks.judge.GameJudger

object ScalaProcessor {
  val executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue[Runnable], new DaemonThreadFactory)
}

class DaemonThreadFactory extends ThreadFactory {

  val delegate = Executors.defaultThreadFactory

  def newThread(r : Runnable) = {
    val thread = delegate.newThread(r)
    thread.setDaemon(true)
    thread
  }
}

class ScalaProcessor[T <: CodeRestrictions[_]](spec : GameSpecification[T]) {

  val className = "ExpressionRunner"
  val fullName = "scalagameunsafe.ExpressionRunner"

  def processCode(code : String) : JudgedTask = {
    val eval = new Eval(None)
    compile(code, eval)
    run(className, eval)
  }

  def run(className : String, eval : Eval) : JudgedTask = {
    val code = eval.findClass(fullName).newInstance.asInstanceOf[T]
    spec.code = code
    executor.submit(new UnsafeCallable(spec)).get(2, TimeUnit.SECONDS)
  }

  private def compile(code : String, eval : Eval) = {
    if (code.contains("finally") || code.contains("catch"))
      throw new SecurityException("Tentativa de executar c&oacute;digo privilegiado dentro de uma task.")
    val wrapped = wrap(className, code, spec.extendsType, spec.runSignature)
    eval.compile(wrapped)
  }

}

class UnsafeCallable(spec : GameSpecification[_ <: CodeRestrictions[_]]) extends Callable[JudgedTask] {

  def call : JudgedTask = {
    try {
      (new GameJudger(spec)).judgement
    } catch {
      case t => { t.printStackTrace; new ExecutionFailure(t) }
    }
  }
}

object TaskRunSecurityManager extends SecurityManager {

  val unsafe = new ThreadLocal[Boolean]

  override def checkPermission(perm : Permission) = {
    handlePermission(perm)
  }

  override def checkPermission(perm : Permission, context : Object) = {
    handlePermission(perm)
  }

  def handlePermission(perm : Permission) =
    if (unsafe.get)
      throw new SecurityException("Tentativa de executar c&oacute;digo privilegiado dentro de uma task.")
}

object Wrappers {

  def wrap(className : String, code : String, extendsType : String, runSignature : String) = {
    "package scalagameunsafe\n" +
      "import vggames.scala.specs._\n" +
      "class " + className + " extends " + extendsType + " {\n" +
      "  def run" + runSignature + " = {\n" +
      code + "\n" +
      "  }\n" +
      "}\n"
  }
}
