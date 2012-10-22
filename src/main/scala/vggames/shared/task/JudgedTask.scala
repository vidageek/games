package vggames.shared.task;

import vggames.shared.player.Player

trait JudgedTask {

  def ok : Boolean

  def reason : String

  def getOk = ok

  def getReason = reason

  def success(f : => Unit) = if (ok) f

  def failure(f : => Unit) = if (!ok) f

  def personalize(player : Option[Player]) : JudgedTask = player.map(a => this).getOrElse(new NotLoggedJudgedTask(this))

  override def toString = reason
}

class NotLoggedJudgedTask(original : JudgedTask) extends JudgedTask {

  def ok = original.ok

  def reason = "Notamos que voc&ecirc; n&atilde;o est&aacute; " +
    "logado. Clique em Login logo acima para acompanhar o seu progresso. <br />" + original.reason

}