package vggames.html

import vggames.shared.task.{ JudgedTask, Task }
import vggames.shared.task.status.Ok

class HtmlTask(val challenge: String, resourceName: String) extends Task {

  def judge(challenge: String): JudgedTask = Ok()

  override def resource = resourceName
}

object HtmlTask {
  def apply(challenge: String, resource: String) = new HtmlTask(challenge, resource)
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
    new HtmlTask(s"""Crie uma tag &lt;$tagName&gt; que envolva o texto <code>conteudo</code>" e com os atributos ${attributesFor(attributes)}""",
      nameFor(tagName, attributes))
}

object EmptyTag {

  import Tags._

  def apply(tagName: String) = new HtmlTask(s"""Crie uma tag <code>&lt;$tagName&gt;</code> vazia""", s"tag-$tagName")

  def apply(tagName: String, attributes: (String, String)*) =
    new HtmlTask(s"""Crie uma tag <code>&lt;$tagName&gt;</code> vazia que tenha os atributos ${attributesFor(attributes)}""",
      nameFor(tagName, attributes))
}

