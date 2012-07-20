package vggames.scala.code

import com.twitter.util.Eval.CompilerException
import vggames.scala.specs.GameSpecification
import vggames.scala.tasks.judge.CompilationFailure
import vggames.scala.tasks.judge.ExecutionFailure
import vggames.shared.task.JudgedTask
import vggames.shared.task.Task

case class Specs2Eval[T <: CodeRestrictions[_]](spec : GameSpecification[T]) extends Task {

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
