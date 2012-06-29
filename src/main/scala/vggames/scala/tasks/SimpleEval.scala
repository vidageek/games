package vggames.scala.tasks

import vggames.shared.task.Task
import vggames.shared.task.JudgedTask
import vggames.scala.ScalaProcessor
import vggames.shared.task.status.Ok
import vggames.shared.task.status.Failed
import com.twitter.util.Eval.CompilerException
import vggames.scala.tasks.judge.CompilationFailure
import vggames.scala.tasks.judge.ExecutionFailure

case class SimpleEval[T](codeToPrepend : String, expectedValue : T, challenge : String) extends Task {

  def judge(challenge : String) : JudgedTask = {
    try {
      val resposta = (new ScalaProcessor).processCode[T]("val a = 1;\nval b = 2;\n" + challenge)
      if (expectedValue == resposta)
        Ok()
      else
        Failed("N&atilde;o somou dois n&uacute;meros")
    } catch {
      case e : CompilerException => CompilationFailure(e)
      case e => ExecutionFailure(e)
    }
  }

  def getChallenge : String = challenge

}