package vggames.meta

import vggames.shared.Game
import vggames.shared.task.Descriptions
import vggames.shared.task.Tasks
import vggames.shared.task.TaskGroup

class MetaGame (descriptions : Descriptions) extends Game {
  override val tasks = new Tasks(
      firstTask
    )
  
  private def firstTask = new TaskGroup("Primeira Tarefa","meta.first",descriptions,
      new MetaTask("Digite o nome do seu jogo"));
  
  
  
  def getDescription(): String = { null }

  def getName(): String = { "Meta" }

}