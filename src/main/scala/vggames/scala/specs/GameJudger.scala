package vggames.scala.specs

import org.specs2.main.Arguments
import org.specs2.reporter.DefaultReporter
import org.specs2.reporter.Exporter
import org.specs2.reporter.TextPrinter
import org.specs2.specification.ExecutedResult
import org.specs2.specification.ExecutedSpecEnd
import org.specs2.specification.ExecutedSpecStart
import org.specs2.specification.ExecutedSpecification
import org.specs2.specification.ExecutedText
import org.specs2.specification.ExecutingSpecification
import vggames.shared.task.status.Failed
import vggames.shared.task.status.Ok
import vggames.shared.task.JudgedTask
import vggames.scala.tasks.judge.ExecutionFailure
import org.specs2.execute.Error

class GameJudger[T](spec : GameSpecification[T]) extends DefaultReporter with TextPrinter with Exporter {

  def judgement : JudgedTask = {
    report(spec)(new Arguments())
    judgementResult.getOrElse(throw new RuntimeException("Não rodou a spec até o fim"))
  }

  private var judgementResult : Option[JudgedTask] = None

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
        case f : ExecutedResult if f.isError => throw f.result.asInstanceOf[Error].e
        case f : ExecutedResult => result += """<li class="spec">""" + f.text + "</li>\n"
        case f : ExecutedSpecEnd => result += "</ul>"
        case f =>
      }
    }
    val executedSpec = spec.executed
    judgementResult = if (fail) Some(Failed(result)) else Some(Ok())
    executedSpec
  }
}