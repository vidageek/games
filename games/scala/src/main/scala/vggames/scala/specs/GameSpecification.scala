package vggames.scala.specs

import vggames.scala.code.{ CodeRestrictions, ScalaProcessor }
import vggames.scala.tasks.judge.{ CompilationFailure, ExecutionFailure }
import vggames.shared.task.{ JudgedTask, Task }
import org.specs2.matcher.MustMatchers
import org.specs2.matcher.MatchResult
import scala.collection.mutable.ListBuffer
import vggames.shared.task.status.Ok
import vggames.shared.task.status.Failed
import vggames.shared.task.status.Failed
import org.specs2.matcher.MustThrownExpectations
import org.specs2.execute.FailureException
import scala.tools.reflect.ToolBoxError

trait GameSpecification[T <: CodeRestrictions[_]] extends Task with MustMatchers with MustThrownExpectations {
  type Code = T

  def run(code : Code, submittedCode : String)(implicit cases : TestRun = new TestRun(this.getClass.getSimpleName)) : TestRun

  def runSignature : String

  def extendsType : String

  def beforeCode : String = ""

  def afterCode : String = ""

  override def resource = ""

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
        case t : Exception => cases.exception(t.getMessage)
      }
    }
  }

  override def judge(challenge : String) : JudgedTask = {
    try {
      new ScalaProcessor(this).processCode(challenge)
    } catch {
      case e : ToolBoxError => CompilationFailure(e)
      case e : Exception => ExecutionFailure(e)
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
    new Failed(s"""Exception foi lan&ccedil;ada durante execu&ccedil;&atilde;o: ${message}""")

  def judgement : JudgedTask = {
    if (exception != null) return exception

    val failure = results.find {
      _ match {
        case _ : Failure => true
        case _ => false
      }
    }
    if (failure.isDefined) {
      val message = results.foldLeft(
        specName + s"\n<ul><li>${shouldName} deve</li>")(_ + _.toString) + "</ul>"
      new Failed(s"Ops! Seu c&oacute;digo n&atilde;o fez tudo que era necess&aacute;rio. <br /> ${message}")
    } else {
      Ok()
    }
  }
}

trait Result

class Success(name : String) extends Result {
  override def toString = s"""<li class="spec">${name}</li>"""
}

class Failure(name : String) extends Result {
  override def toString = s"""<li class="spec-fail spec">${name}</li>"""
}
