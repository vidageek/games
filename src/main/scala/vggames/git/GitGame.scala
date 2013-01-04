package vggames.git

import vggames.shared.task.Descriptions
import vggames.shared.Game
import vggames.shared.task.Tasks
import vggames.shared.task.TaskGroup

class GitGame(descriptions : Descriptions) extends Game {

  val tasks = new Tasks(
    init)

  def init = {
    val tasks = (EmptyGit() ~ Init("repositorio")).tasks ++ (EmptyGit() ~ Init("repo2")).tasks
    new TaskGroup("Criar um reposit&oacute;rio", "git.init", descriptions, tasks : _*)
  }

  def getDescription = "Um jogo muito legal para aprender Git"

  def getName = "Git"

}