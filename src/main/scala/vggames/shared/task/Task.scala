package vggames.shared.task;

trait Task[T] {

  def judge(challenge : String) : JudgedTask

  def getChallenge : String

  def resource : String

  final def getResource = resource

  override def toString : String = getChallenge

  def extraData : Option[T] = None

  final def getExtraData : T = extraData.get
}
