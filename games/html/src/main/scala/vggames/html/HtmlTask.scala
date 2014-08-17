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

object SimpleTag {
  def apply(tagName: String, attributes: (String, String)*) =
    new HtmlTask(s"""Crie uma tag &lt;$tagName&gt; que envolva o texto "conteudo" """ +
      (if (attributes.isEmpty) "" else s"e com os atributos ${attributesFor(attributes)}"),
      nameFor(tagName, attributes))

  private def nameFor(tagName: String, attributes: Seq[(String, String)]) =
    if (attributes.isEmpty) s"tag-$tagName"
    else s"tag-$tagName-${attributes.map(_._1).mkString("-")}"

  private def attributesFor(attributes: Seq[(String, String)]) = attributes.map(t => s"""${t._1}="${t._2}"""").mkString(", ")

}

