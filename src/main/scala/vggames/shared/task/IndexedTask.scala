package vggames.shared.task;

import vggames.shared.task.status.Error

class IndexedTask(delegate : TaskWithDescription, index : Int) extends TaskWithDescription {

  def getIndex : Int = index

  def judge(challenge : String) : JudgedTask = {
    try {
      delegate.judge(challenge)
    } catch { case e => new Error(e) }
  }

  def resource = delegate.resource

  def getDescription : String = delegate.getDescription

  def getChallenge : String = delegate.getChallenge

  override def toString : String = delegate.toString

  def getGroupName : String = delegate.getGroupName

  def getGroupCode = delegate.getGroupCode

}
