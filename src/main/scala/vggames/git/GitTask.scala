package vggames.git

import vggames.shared.task.Task
import vggames.shared.task.status.Ok

case class GitTask() extends Task {

  def judge(challenge : String) = Ok()

  def getChallenge = "bla bla bla ble"

  def resource = ""
}