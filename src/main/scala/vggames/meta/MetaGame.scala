package vggames.meta

import vggames.shared.Game
import vggames.shared.task.Descriptions
import vggames.shared.task.Tasks

class MetaGame (descriptions : Descriptions) extends Game {
  override val tasks = new Tasks(
    )
  
  def getDescription(): String = { null }

  def getName(): String = { "Meta" }

}