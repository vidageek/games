package vggames.shared.task

class GroupedTask(val group : TaskGroup, task : Task) {

  def judge(challenge : String) = task.judge(challenge)

  def getChallenge = task.getChallenge

  def resource = task.resource

  def extraData = task.extraData

}