package vggames.shared.task;

import scalatags._

trait JudgedTask {

  def ok : Boolean

  def reason : String

  def success(f : => Unit) = if (ok) f

  def failure(f : => Unit) = if (!ok) f

  override def toString = reason
}

class NotLoggedJudgedTask(original : JudgedTask) extends JudgedTask {

  def ok = original.ok

  def reason =
    p("Notamos que você não está logado. Clique em ",
      strong(
        a(href := "#logon-provider", "data-toggle".attr := "modal")("Login")),
      " para acompanhar o seu progresso.").toString + original.reason
}