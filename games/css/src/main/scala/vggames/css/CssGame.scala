package vggames.css

import vggames.shared.task.Descriptions
import vggames.shared.task.Tasks
import vggames.shared.task.TaskGroup
import vggames.shared.GameEngine

class CssGame extends GameEngine {

  val tasks : Tasks = new Tasks(addTestTask)

  def addTestTask =
    new TaskGroup("Css test", "css.test",
      new CssTask("bla"))

  def getDescription = "Um jogo muito legal para aprender CSS"

  def getName : String = "CSS"

  def path = "css"

}