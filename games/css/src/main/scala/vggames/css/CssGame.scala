package vggames.css

import vggames.shared.task.Descriptions
import br.com.caelum.vraptor.ioc.Component
import vggames.shared.Game
import vggames.shared.task.Tasks
import vggames.shared.task.TaskGroup

class CssGame(descriptions : Descriptions) extends Game {

  val tasks : Tasks = new Tasks(addTestTask)

  def addTestTask =
    new TaskGroup("Css test", "css.test", descriptions,
      new CssTask("bla"))

  def getDescription = "Um jogo muito legal para aprender CSS"

  def getName : String = "CSS"

}