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
  def apply(tagName: String) =
    new HtmlTask(s"""Crie uma tag &lt;$tagName&gt; que envolva o texto "conteudo" """, s"tag-$tagName")

}

