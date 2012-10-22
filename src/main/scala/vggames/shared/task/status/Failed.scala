package vggames.shared.task.status;

import vggames.shared.task.JudgedTask

case class Failed(message : String) extends JudgedTask {

  def this(fails : Faileds) = this(fails.reason)

  def ok = false

  def reason = message
}
