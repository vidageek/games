package vggames.shared.task;

class TaskWithDescription(delegate : IndexedTask, descriptions : Descriptions) extends Task {

  def description = descriptions.forGroup(groupCode)

  def groupName = delegate.groupName

  def groupCode = delegate.groupCode

  def group = delegate.group

  def judge(challenge : String) = delegate.judge(challenge)

  def challenge = delegate.challenge

  def index = delegate.index

  def resource = delegate.resource

  override def extraData = delegate.extraData

}
