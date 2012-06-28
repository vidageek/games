package vggames.scala.tasks.judge

import vggames.shared.task.status.Failed
import vggames.shared.task.JudgedTask

case class CompilationFailure(failure : Throwable) extends JudgedTask {

  def getOk = false

  def getReason = "<pre>Falha de compilação: \n" + failure.getMessage + "</pre>"

}