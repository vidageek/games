package vggames.shared.task.status;

import vggames.shared.task.JudgedTask

class Error(e : Throwable) extends JudgedTask {

  def ok = false

  def reason = e.getMessage
}
