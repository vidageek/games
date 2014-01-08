package vggames.scala.tasks.judge

import vggames.shared.task.JudgedTask

case class ExecutionFailure(failure : Throwable) extends JudgedTask {

  def ok = false

  def reason = "Exception foi lan&ccedil;ada durante execu&ccedil;&atilde;o:  \n" + failure.getMessage

  override def toString = reason

}