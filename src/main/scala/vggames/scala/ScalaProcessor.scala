package vggames.scala

import java.security.Permission
import java.util.concurrent.{ TimeUnit, ThreadPoolExecutor, ThreadFactory, Executors, Callable, SynchronousQueue }

import com.twitter.util.Eval

import ScalaProcessor.executor

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

class ScalaProcessor {

  val className = "ExpressionRunner"

  def processCode[T](code : String) : T = {
    val eval = new Eval(None)
    compile(code, eval)
    run(className, eval)
  }

  def run[T](className : String, eval : Eval) : T = {
    val code = eval.findClass("scalagameunsafe." + className).newInstance.asInstanceOf[() => Any]
    executor.submit(new UnsafeCallable[T](code)).get(2, TimeUnit.SECONDS)
  }

  private def compile(code : String, eval : Eval) = {
    val wrapped = wrap(className, code)
    eval.compile(wrapped)
  }

  def wrap(className : String, code : String) = {
    "package scalagameunsafe\n" +
      "class " + className + " extends (() => Any) {\n" +
      "  def apply() = {\n" +
      code + "\n" +
      "  }\n" +
      "}\n"
  }

}

class UnsafeCallable[T](code : () => Any) extends Callable[T] {

  def call : T = {
    System.setSecurityManager(TaskRunSecurityManager)
    TaskRunSecurityManager.unsafe.set(true)
    try {
      code().asInstanceOf[T]
    } finally {
      TaskRunSecurityManager.unsafe.set(false)
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
    if (unsafe.get) throw new SecurityException("Tentativa de executar c&oacute;digo privilegiado dentro de uma task.")

}