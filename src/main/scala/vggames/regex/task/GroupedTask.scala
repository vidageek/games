package vggames.regex.task

import vggames.task.Task
import vggames.task.TaskWithDescription

class GroupedTask(group: TaskGroup, task: Task) extends TaskWithDescription {

  def judge(challenge: String) = task.judge(challenge)

  def getChallenge = task.getChallenge

  def getDescription = group.getDescription

  override def toString = task.toString()

  def getGroupName = group.getName
}