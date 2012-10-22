package vggames.shared.task;

trait Task {

  def judge(challenge : String) : JudgedTask

  def getChallenge : String

  def resource : String

  final def getResource = resource

  override def toString : String = getChallenge
}
