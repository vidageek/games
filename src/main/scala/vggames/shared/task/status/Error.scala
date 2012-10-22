package vggames.shared.task.status;

import vggames.shared.task.JudgedTask

class Error(e : Throwable) extends JudgedTask {

  def ok : Boolean = false

  def reason : String = e.getMessage
}
