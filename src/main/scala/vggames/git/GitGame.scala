package vggames.git

import vggames.shared.task.Descriptions
import vggames.shared.Game
import vggames.shared.task.Tasks
import vggames.shared.task.TaskGroup

class GitGame(descriptions : Descriptions) extends Game {

  val tasks = new Tasks(
    new TaskGroup("teste", "test", descriptions))

  def getDescription = "Um jogo muito legal para aprender Git"

  def getName = "Git"

}