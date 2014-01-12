package vggames.shared.task;

trait Task {

  def judge(challenge : String) : JudgedTask

  def challenge : String

  def resource : String

  override def toString : String = challenge

  def extraData : Option[Any] = None
}
