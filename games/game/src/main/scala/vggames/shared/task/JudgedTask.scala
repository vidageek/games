package vggames.shared.task;

trait JudgedTask {

  def ok : Boolean

  def reason : String

  def getOk = ok

  def getReason = reason

  def success(f : => Unit) = if (ok) f

  def failure(f : => Unit) = if (!ok) f

  override def toString = reason
}

class NotLoggedJudgedTask(original : JudgedTask) extends JudgedTask {

  def ok = original.ok

  def reason = "Notamos que voc&ecirc; n&atilde;o est&aacute; " +
    "logado. Clique em Login logo acima para acompanhar o seu progresso. <br />" + original.reason

}