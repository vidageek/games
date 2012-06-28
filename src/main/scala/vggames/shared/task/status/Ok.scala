package vggames.shared.task.status;

import vggames.shared.task.JudgedTask;

case class Ok() extends JudgedTask {

  def getOk = true

  def getReason = "ok!"

}
