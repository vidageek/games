package vggames.shared.task.status;

import vggames.shared.task.JudgedTask

case class Ok() extends JudgedTask {

  def ok = true

  def reason = "Ok!"

}
