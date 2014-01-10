package vggames.shared.task

class GroupedTask[T](val group : TaskGroup, task : Task[T]) {

  def judge(challenge : String) = task.judge(challenge)

  def getChallenge = task.getChallenge

  def resource = task.resource

}