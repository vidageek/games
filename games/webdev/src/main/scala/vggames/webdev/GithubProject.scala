package vggames.webdev

import vggames.shared.task.Task
import vggames.shared.task.TaskGroup
import vggames.shared.task.status.Ok

class GithubProject(name : String, id : String)
    extends TaskGroup(name, id, new GithubTask()) {

}

class GithubTask() extends Task {

  def judge(challenge : String) = Ok()

  def challenge = "Crie um projeto no github que resolva o problema apresentado ao lado e coloque a url abaixo"

  def resource = ""

}