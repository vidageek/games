package vggames.git

import vggames.shared.task.Task
import vggames.shared.task.status.Ok

case class GitTask(original : Git, expected : Git) extends Task[Git] {

  def judge(challenge : String) = {

    Ok()
  }

  def getChallenge = "bla bla bla ble"

  def resource = ""

  override def extraData = Option(original)
}