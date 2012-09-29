package vggames.scala.specs

import org.specs2.mutable.Specification

import com.twitter.util.Eval.CompilerException

import vggames.scala.code.{CodeRestrictions, ScalaProcessor}
import vggames.scala.tasks.judge.{CompilationFailure, ExecutionFailure}
import vggames.shared.task.{JudgedTask, Task}

trait GameSpecification[T <: CodeRestrictions[_]] extends Specification with Task {

  var code : T = _

  def runSignature : String

  def extendsType : String

  override def judge(challenge : String) : JudgedTask = {
    try {
      new ScalaProcessor(this).processCode(challenge)
    } catch {
      case e : CompilerException => CompilationFailure(e)
      case e => ExecutionFailure(e)
    }
  }

}