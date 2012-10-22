package vggames.scala.tasks.judge

import vggames.shared.task.JudgedTask

case class CompilationFailure(failure : Throwable) extends JudgedTask {

  def ok = false

  def reason = "<pre>Falha de compila&ccedil;&atilde;o: \n" + failure.getMessage + "</pre>"

  override def toString = reason

}