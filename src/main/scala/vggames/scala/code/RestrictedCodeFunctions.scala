package vggames.scala.code

import akka.dispatch.Await
import akka.pattern.ask
import akka.util.duration._
import akka.util.Timeout
import vggames.scala.actors._
import vggames.scala.tasks.judge.ExecutionFailure

sealed trait CodeRestrictions[+R] {
  implicit val timeout = Timeout(5 seconds)

  def restrict[V >: R](code : => V) : V = {
    val future = GameMaster.master ? Run(() => {
      System.setSecurityManager(TaskRunSecurityManager)
      TaskRunSecurityManager.unsafe.set(true)
      try {
        code
      } finally {
        TaskRunSecurityManager.unsafe.set(false)
      }
    })

    Await.result(future, timeout.duration) match {
      case e : ExecutionFailure => throw e.failure
      case ok => ok.asInstanceOf[V]
    }
  }
}

trait RestrictedFunction1[-T1, +R] extends Function1[T1, R] with CodeRestrictions[R] {
  override def toString = "<restricted Function1>"
  override def apply(v1 : T1) : R = restrict(run(v1))

  def run(v1 : T1) : R
}

trait RestrictedFunction2[-T1, -T2, +R] extends Function2[T1, T2, R] with CodeRestrictions[R] {
  override def toString = "<restricted Function2>"
  override def apply(v1 : T1, v2 : T2) : R = restrict(run(v1, v2))

  def run(v1 : T1, v2 : T2) : R
}
