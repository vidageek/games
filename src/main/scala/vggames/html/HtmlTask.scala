package vggames.html

import vggames.shared.task.Task
import vggames.shared.task.status.Ok
import vggames.shared.task.JudgedTask

class HtmlTask(challenge : String) extends Task {

  def judge(challenge : String) : JudgedTask = Ok()

  def getChallenge : String = challenge

}