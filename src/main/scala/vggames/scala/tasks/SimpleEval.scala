package vggames.scala.tasks

import com.twitter.util.Eval.CompilerException

import vggames.scala.tasks.judge.{ ExecutionFailure, CompilationFailure }
import vggames.scala.ScalaProcessor
import vggames.shared.task.status.{ Ok, Failed }
import vggames.shared.task.{ Task, JudgedTask }

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