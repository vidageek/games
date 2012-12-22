package vggames.git

import vggames.shared.task.Task
import vggames.shared.task.status.Ok
import vggames.shared.task.status.Failed
import vggames.shared.task.JudgedTask

case class GitTask(original : Git, expected : Git) extends Task[Git] {

  def judge(challenge : String) : JudgedTask = {
    val command = Command(challenge)
    if (!command.isDefined) return Failed("Comando [%s] n&atilde;o foi reconhecido.".format(challenge))

    val differences = (original ~ command.get).diff(expected)

    if (differences.isEmpty) Ok() else Failed(differences.mkString("<br />"))
  }

  def getChallenge = expected.command.challenge

  def resource = ""

  override def extraData = Option(original)
}