package vggames.css

import vggames.shared.task.Task
import vggames.shared.task.JudgedTask
import vggames.shared.task.status.Ok

class CssTask(challenge : String) extends Task {

  def judge(challenge : String) : JudgedTask = Ok()

  def getChallenge = challenge

  def resource = "first"
}