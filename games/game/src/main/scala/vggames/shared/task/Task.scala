package vggames.shared.task;

import vggames.shared.log.LogItem

trait Task {

  def judge(challenge : String) : JudgedTask

  def challenge : String

  def resource : String

  override def toString : String = challenge

  def extraData : Option[Any] = None

  def extraLog(playerId : Option[Long], challenge : String, gameName : String, judgedTask : JudgedTask) : Option[LogItem] = None
}
