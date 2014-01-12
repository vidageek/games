package vggames.html

import vggames.shared.task.{ JudgedTask, Task }
import vggames.shared.task.status.Ok

class HtmlTask(val challenge : String, resourceName : String) extends Task {

  def judge(challenge : String) : JudgedTask = Ok()

  override def resource = resourceName
}

object HtmlTask {
  def apply(challenge : String, resource : String) = new HtmlTask(challenge, resource)
}

