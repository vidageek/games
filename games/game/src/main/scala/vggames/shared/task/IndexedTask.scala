package vggames.shared.task;

import vggames.shared.task.status.Error
import scala.util.Try

class IndexedTask(val group : TaskGroup, delegate : Task, val index : Int) {

  def judge(challenge : String) : JudgedTask = {
    Try(delegate.judge(challenge)).
      recover { case e : Exception => new Error(e) }.
      get
  }

  def resource = delegate.resource

  def challenge : String = delegate.challenge

  def groupName = group.htmlName

  def groupCode = group.groupName

  override def toString : String = delegate.toString

  def extraData = delegate.extraData

}
