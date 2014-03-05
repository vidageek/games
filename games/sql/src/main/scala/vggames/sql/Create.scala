package vggames.sql

import vggames.shared.task.Task
import vggames.shared.task.JudgedTask
import vggames.shared.task.status.Ok

class Create(nome : String) extends Task {

  def judge(challenge : String) = Ok()

  def challenge = s"Crie a tabela $nome"

  def resource = ""

  override def extraData : Option[Any] = None
}