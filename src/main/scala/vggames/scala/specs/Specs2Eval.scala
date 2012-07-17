package vggames.scala.specs

import vggames.shared.task.Task
import vggames.shared.task.JudgedTask
import org.specs2.specification.SpecificationStructure
import org.specs2.specification.BaseSpecification
import org.specs2.mutable.Specification
import org.specs2.reporter.Reporter
import org.specs2.reporter.ConsoleReporter
import vggames.shared.task.status.Ok
import org.specs2.main.Arguments
import org.specs2.reporter.DefaultReporter
import org.specs2.reporter.Exporting
import org.specs2.specification.ExecutingSpecification
import org.specs2.specification.ExecutedSpecification
import org.specs2.reporter.Exporter
import org.specs2.reporter.TextExporting
import org.specs2.reporter.TextPrinter
import org.specs2.specification.ExecutedSpecStart
import org.specs2.specification.ExecutedSpecEnd
import org.specs2.specification.ExecutedText
import org.specs2.specification.ExecutedResult
import org.specs2.execute.Success
import vggames.shared.task.status.Failed

case class Specs2Eval(challenge : String, spec : Specification) extends Task {

  def getChallenge = challenge

  def judge(challenge : String) : JudgedTask = {
    (new GameJudger(spec)).judgement
  }
}

class GameJudger(spec : Specification) extends DefaultReporter with TextPrinter with Exporter {

  var judgement : JudgedTask = Ok()

  report(spec)(new Arguments())

  override def export(implicit args : Arguments) : ExecutingSpecification => ExecutedSpecification = (spec : ExecutingSpecification) => {
    var result = ""
    var fail = false
    spec.foreach { (name, fragments) =>
      result += name.name
      fragments.foreach {
        case f : ExecutedSpecStart => result += "\n<ul>\n"
        case f : ExecutedText => result += "<li>" + f.text + "</li>\n"
        case f : ExecutedResult if f.isFailure => {
          result += """<li class="spec-fail spec">""" + f.text + "</li>\n"
          fail = true
        }
        case f : ExecutedResult => result += """<li class="spec">""" + f.text + "</li>\n"
        case f : ExecutedSpecEnd => result += "</ul>"
        case _ =>
      }
    }
    if (fail) {
      judgement = Failed(result)
    }
    spec.executed
  }
}

object A {

  def main(args : Array[String]) {
    println(Specs2Eval("", new ExampleSpec).judge(""))
  }
}