package vggames.html

import vggames.shared.task.{ JudgedTask, Task }
import vggames.shared.task.status.Ok
import java.util.Scanner
import scala.util.Try

class HtmlTask(val challenge: String, resourceName: String, prefill: Option[String] = None) extends Task {

  def judge(challenge: String): JudgedTask = Ok()

  override def resource = resourceName

  override def extraData = prefill

}

object HtmlTask {
  def apply(challenge: String, resource: String) = new HtmlTask(challenge, resource)
}

object TextToHtml {

  private val removeTags = "<[^>]+>".r

  def apply(resource: String) = {
    val prefill = Try(new Scanner(getClass().getResourceAsStream(s"/html/$resource.html")).useDelimiter("$$").next())
    new HtmlTask("Adicione tags ao texto abaixo para ele fique igual ao exemplo", resource,
      prefill.toOption.map(removeTags.replaceAllIn(_, "")))
  }

}

object Tags {
  def nameFor(tagName: String, attributes: Seq[(String, String)]) =
    s"tag-$tagName-${attributes.map(_._1).mkString("-")}"

  def attributesFor(attributes: Seq[(String, String)]) = attributes.map(t => s"""<code>${t._1}="${t._2}"</code>""").mkString(", ")
}

object SimpleTag {

  import Tags._

  def apply(tagName: String) = new HtmlTask(s"""Crie uma tag <code>&lt;$tagName&gt;</code> que envolva o texto <code>conteudo</code> """, s"tag-$tagName")

  def apply(tagName: String, attributes: (String, String)*) =
    new HtmlTask(s"""Crie uma tag <code>&lt;$tagName&gt;</code> que envolva o texto <code>conteudo</code> e com os atributos ${attributesFor(attributes)}""",
      nameFor(tagName, attributes))
}

object EmptyTag {

  import Tags._

  def apply(tagName: String) = new HtmlTask(s"""Crie uma tag <code>&lt;$tagName&gt;</code> vazia""", s"tag-$tagName")

  def apply(tagName: String, attributes: (String, String)*): HtmlTask = apply(tagName, nameFor(tagName, attributes), attributes: _*)

  def apply(tagName: String, name: String, attributes: (String, String)*) =
    new HtmlTask(s"""Crie uma tag <code>&lt;$tagName&gt;</code> vazia que tenha os atributos ${attributesFor(attributes)}""",
      name)
}

