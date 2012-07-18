package vggames.scala

import java.security.Permission
import java.util.concurrent.{ TimeUnit, ThreadPoolExecutor, ThreadFactory, Executors, Callable, SynchronousQueue }
import com.twitter.util.Eval
import ScalaProcessor.executor
import vggames.scala.specs.GameSpecification
import vggames.shared.task.JudgedTask
import vggames.scala.specs.GameJudger
import org.specs2.specification.StandardFragments
import vggames.scala.tasks.judge.ExecutionFailure
import vggames.scala.tasks.judge.ExecutionFailure

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

class ScalaProcessor[T](spec : GameSpecification[T]) {

  val className = "ExpressionRunner"

  def processCode(code : String) : JudgedTask = {
    val eval = new Eval(None)
    compile(code, eval)
    run(className, eval)
  }

  def run(className : String, eval : Eval) : JudgedTask = {
    val code = eval.findClass("scalagameunsafe." + className).newInstance.asInstanceOf[T]
    spec.code = code
    executor.submit(new UnsafeCallable(spec)).get(2, TimeUnit.SECONDS)
  }

  private def compile(code : String, eval : Eval) = {
    if (code.contains("finally") || code.contains("catch"))
      throw new SecurityException("Tentativa de executar c&oacute;digo privilegiado dentro de uma task.")
    val wrapped = spec.wrap(className, code)
    eval.compile(wrapped)
  }

}

class UnsafeCallable(spec : GameSpecification[_]) extends Callable[JudgedTask] {

  def call : JudgedTask = {
    val old = System.getSecurityManager
    System.setSecurityManager(TaskRunSecurityManager)
    TaskRunSecurityManager.unsafe.set(true)
    try {
      val a = (new GameJudger(spec)).judgement
      a
    } catch {
      case t => { t.printStackTrace; new ExecutionFailure(t) }
    } finally {
      TaskRunSecurityManager.unsafe.set(false)
      System.setSecurityManager(old)
    }
  }
}

object TaskRunSecurityManager extends SecurityManager {

  val unsafe = new ThreadLocal[Boolean]
  val oldSecurityManager = System.getSecurityManager

  override def checkPermission(perm : Permission) = {
    handlePermission(perm)
  }

  override def checkPermission(perm : Permission, context : Object) = {
    handlePermission(perm)
  }

  def handlePermission(perm : Permission) =
    if (unsafe.get && !allowed(perm)) {
      throw new SecurityException("Tentativa de executar c&oacute;digo privilegiado dentro de uma task.")
    }

  def allowed(perm : Permission) : Boolean = {
    perm.getName.contains("specs") ||
      perm.getName.contains("modifyThread") ||
      perm.getName.contains("user.dir") ||
      perm.getName.contains("line.separator") ||
      perm.getName.contains("vggames")
  }
}