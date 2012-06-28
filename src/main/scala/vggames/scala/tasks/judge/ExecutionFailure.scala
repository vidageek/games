package vggames.scala.tasks.judge

import vggames.shared.task.JudgedTask

case class ExecutionFailure(failure : Throwable) extends JudgedTask {

  def getOk = false

  def getReason = "Exception foi lançada durante execução:  \n" + failure.getMessage

}