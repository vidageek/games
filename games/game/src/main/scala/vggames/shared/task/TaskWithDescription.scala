package vggames.shared.task;

class TaskWithDescription(delegate : IndexedTask, descriptions : Descriptions) extends Task {

  def getDescription = descriptions.forGroup(getGroupCode)

  def getGroupName = delegate.groupName

  def getGroupCode = delegate.groupCode

  def group = delegate.group

  def judge(challenge : String) = delegate.judge(challenge)

  def getChallenge = delegate.getChallenge

  def getIndex = delegate.getIndex

  def resource = delegate.resource

  override def extraData = delegate.extraData

}
