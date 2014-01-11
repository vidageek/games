package vggames.shared.task;

import vggames.shared.task.status.Error

class IndexedTask(delegate : GroupedTask, index : Int) {

  def getIndex : Int = index

  def judge(challenge : String) : JudgedTask = {
    try {
      delegate.judge(challenge)
    } catch { case e : Exception => new Error(e) }
  }

  def resource = delegate.resource

  def getChallenge : String = delegate.getChallenge

  def groupName = delegate.group.getName

  def groupCode = delegate.group.groupName

  def group = delegate.group

  override def toString : String = delegate.toString

  def extraData = delegate.extraData

}
