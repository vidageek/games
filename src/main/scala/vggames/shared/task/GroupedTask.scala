package vggames.shared.task

class GroupedTask(group : TaskGroup, task : Task) extends TaskWithDescription {

  def judge(challenge : String) = task.judge(challenge)

  def getChallenge = task.getChallenge

  def getDescription = group.getDescription

  override def toString = task.toString

  def getGroupName = group.getName

  def getGroupCode = group.groupName
}