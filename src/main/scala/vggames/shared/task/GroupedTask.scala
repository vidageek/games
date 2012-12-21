package vggames.shared.task

class GroupedTask[T](group : TaskGroup, task : Task[T]) extends TaskWithDescription[T] {

  def judge(challenge : String) = task.judge(challenge)

  def getChallenge = task.getChallenge

  def getDescription = group.getDescription

  def getGroupName = group.getName

  def getGroupCode = group.groupName

  def resource = task.resource

  override def extraData = task.extraData
}