package vggames.scala.specs

import vggames.scala.ScalaProcessor
import vggames.shared.task.{ Task, JudgedTask }
import com.twitter.util.Eval.CompilerException
import vggames.scala.tasks.judge.ExecutionFailure
import vggames.scala.tasks.judge.CompilationFailure

case class Specs2Eval[T](spec : GameSpecification[T]) extends Task {

  def getChallenge = spec.challenge

  def judge(challenge : String) : JudgedTask = {
    try {
      new ScalaProcessor(spec).processCode(challenge)
    } catch {
      case e : CompilerException => CompilationFailure(e)
      case e => ExecutionFailure(e)
    }
  }
}
