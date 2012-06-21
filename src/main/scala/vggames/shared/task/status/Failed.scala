package vggames.shared.task.status;

import vggames.shared.task.JudgedTask;

class Failed(reason : String) extends JudgedTask {

  def this(fails : Faileds) = this(fails.getReason)

  def getOk = false

  def getReason = reason

  override def toString = reason

}
