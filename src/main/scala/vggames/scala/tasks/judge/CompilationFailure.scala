package vggames.scala.tasks.judge

import vggames.shared.task.JudgedTask

case class CompilationFailure(failure : Throwable) extends JudgedTask {

  def getOk = false

  def getReason = "<pre>Falha de compila&ccedil;&atilde;o: \n" + failure.getMessage + "</pre>"

  override def toString = getReason

}