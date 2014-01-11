package vggames.html

import vggames.shared.task.{ JudgedTask, Task }
import vggames.shared.task.status.Ok

class HtmlTask(challenge : String, resourceName : String) extends Task {

  def judge(challenge : String) : JudgedTask = Ok()

  def getChallenge : String = challenge

  override def resource = resourceName
}

object HtmlTask {
  def apply(challenge : String, resource : String) = new HtmlTask(challenge, resource)
}

