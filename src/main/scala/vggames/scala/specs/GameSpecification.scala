package vggames.scala.specs

import org.specs2.mutable.Specification
import com.twitter.util.Eval.CompilerException
import vggames.scala.code.{ CodeRestrictions, ScalaProcessor }
import vggames.scala.tasks.judge.{ CompilationFailure, ExecutionFailure }
import vggames.shared.task.{ JudgedTask, Task }
import org.specs2.matcher.ShouldMatchers
import org.specs2.matcher.MustMatchers
import org.specs2.matcher.MatchResult
import scala.collection.mutable.ListBuffer
import vggames.shared.task.status.Ok
import vggames.shared.task.status.Failed
import vggames.shared.task.status.Failed
import org.specs2.matcher.MustThrownExpectations
import org.specs2.execute.FailureException

trait GameSpecification[T <: CodeRestrictions[_]] extends Task with MustMatchers with MustThrownExpectations {

  type Code = T

  def run(code : Code, submittedCode : String)(implicit cases : TestRun = new TestRun(this.getClass.getSimpleName)) : TestRun

  def runSignature : String

  def extendsType : String

  implicit def addSpecName(name : String)(implicit cases : TestRun) = new {
    def should(loadAssertions : => Unit) = {
      cases.add(name)
      loadAssertions
      cases
    }
  }

  implicit def addAssertion(assertionName : String)(implicit cases : TestRun) = new {
    def in(a : => MatchResult[_]) = {
      try {
        a
        cases.success(assertionName)
      } catch {
        case t : FailureException => cases.failure(assertionName)
        case t => cases.exception(t.getMessage)
      }
    }
  }

  override def judge(challenge : String) : JudgedTask = {
    try {
      new ScalaProcessor(this).processCode(challenge)
    } catch {
      case e : CompilerException => CompilationFailure(e)
      case e => ExecutionFailure(e)
    }
  }
}

class TestRun(specName : String) {

  var shouldName : String = _

  var exception : Failed = _

  val results = ListBuffer[Result]()

  def add(shouldName : String) = this.shouldName = shouldName

  def success(assertionName : String) = results += new Success(assertionName)

  def failure(assertionName : String) = results += new Failure(assertionName)

  def exception(message : String) : Unit = if (exception == null) exception =
    new Failed("""Exception foi lan&ccedil;ada durante execu&ccedil;&atilde;o: %s""".format(message))

  def judgement : JudgedTask = {
    if (exception != null) return exception

    val failure = results.find {
      _ match {
        case _ : Failure => true
        case _ => false
      }
    }
    if (failure.isDefined) {
      val message = results.foldLeft(specName + "\n<ul><li>%s deve</li>".
        format(shouldName))(_ + _.toString) + "</ul>"
      new Failed(message)
    } else {
      Ok()
    }
  }
}

trait Result

class Success(name : String) extends Result {
  override def toString = """<li class="spec">%s</li>""".format(name)
}

class Failure(name : String) extends Result {
  override def toString = """<li class="spec-fail spec">%s</li>""".format(name)
}
