package vggames.shared.task;

import vggames.shared.task.status.Error
import scala.util.Try

class Exercise(val group : TaskGroup, descriptions : Descriptions, delegate : Task, val index : Int) {

  def judge(challenge : String) : JudgedTask = {
    Try(delegate.judge(challenge)).
      recover { case e : Exception => new Error(e) }.
      get
  }

  def resource = delegate.resource

  def challenge : String = delegate.challenge

  def groupName = group.htmlName

  def groupId = group.id

  def description = descriptions.forGroup(groupId)

  override def toString : String = delegate.toString

  def extraData = delegate.extraData

  def extraLog(playerId : Option[Long], challenge : String, gameName : String, judgedTask : JudgedTask) =
    delegate.extraLog(playerId, challenge, gameName, judgedTask : JudgedTask)
}
