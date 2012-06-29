package vggames.shared.task.status;

import vggames.shared.task.JudgedTask

class Error(e : Throwable) extends JudgedTask {

  def getOk : Boolean = false

  def getReason : String = e.getMessage
}
