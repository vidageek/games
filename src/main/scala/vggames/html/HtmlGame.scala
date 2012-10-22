package vggames.html

import vggames.shared.task.Descriptions
import vggames.shared.Game
import vggames.shared.task.Tasks
import vggames.shared.task.TaskGroup

class HtmlGame(descriptions : Descriptions) extends Game {

  override val tasks = new Tasks(
    new TaskGroup("teste", "test", descriptions,
      new HtmlTask("bla")))

  def getDescription = "Um jogo muito legal para aprender Html, focado em Html5"

  def getName = "Html"

}