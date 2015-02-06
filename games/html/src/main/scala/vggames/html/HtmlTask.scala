package vggames.html

import vggames.shared.task.{ JudgedTask, Task }
import vggames.shared.task.status.Ok
import java.util.Scanner
import scala.util.Try
import scala.util.parsing.combinator.RegexParsers

class HtmlTask(val challenge: String, resourceName: String, val data: HtmlExtraData = HtmlExtraData(None)) extends Task {

  def judge(challenge: String): JudgedTask = Ok()

  override def resource = resourceName

  override def extraData = Option(data)

  def withContext(context: Context) = new HtmlTask(challenge, resourceName, HtmlExtraData(data.prefill, context))

}

object HtmlTask {
  def apply(challenge: String, resource: String) = new HtmlTask(challenge, resource)
}

object TextToHtml {

  private val removePatterns = List(
    ("""<a href="([^"]+)"[^>]*>""".r, "$1 "),
    ("""<img src="([^"]+)" alt="([^"]+)"[^>]*>""".r, "$1 $2 "),
    ("<[^>]+>".r, ""))

  def apply(resource: String) = {
    val prefill = Try(new Scanner(getClass().getResourceAsStream(s"/html/$resource.html")).useDelimiter("$$").next())
    new HtmlTask("Adicione tags ao texto abaixo para ele fique igual ao exemplo", resource,
      HtmlExtraData(removeTags(prefill)))
  }

  def removeTags(prefill: Try[String]) = removePatterns.foldLeft(prefill.toOption) {
    case (prefill, (pattern, replaceValue)) => prefill.map(pattern.replaceAllIn(_, replaceValue))
  }

}

object Page {
  def apply(resource: String) = new HtmlTask("Escreva o html de uma página completa que fique igual ao exemplo", resource,
    TextToHtml(resource).data) withContext Empty
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

class Context(val before: String, val after: String)
object Empty extends Context("", "")
object Doctype extends Context("<!DOCTYPE html>\n", "")
object Html extends Context(s"${Doctype.before}\n<html>", "</html>")
object Head extends Context(s"${Html.before}<head>", s"</head><body></body>${Html.after}")
object Body extends Context(s"${Html.before}<head><title>title</title></head><body>", s"</body>${Html.after}")
object Table extends Context(s"${Body.before}<table>", s"</table>${Body.after}")
object Tr extends Context(s"${Table.before}<tr>", s"</tr>${Table.after}")

case class HtmlExtraData(prefill: Option[String], context: Context = Body)


