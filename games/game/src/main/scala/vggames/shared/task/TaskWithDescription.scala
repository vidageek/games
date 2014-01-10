package vggames.shared.task;

class TaskWithDescription[T](delegate : IndexedTask[T], descriptions : Descriptions) extends Task[T] {

  def getDescription = descriptions.forGroup(getGroupName)

  def getGroupName = delegate.groupName

  def getGroupCode = delegate.groupCode

  def judge(challenge : String) = delegate.judge(challenge)

  def getChallenge = delegate.getChallenge

  def getIndex = delegate.getIndex

  def resource = delegate.resource

}
