package vggames.shared.task;

trait JudgedTask {

  def getOk : Boolean

  def getReason : String

  def success(f : => Unit) = if (getOk) f

  def failure(f : => Unit) = if (!getOk) f
}
