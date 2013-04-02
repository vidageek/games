package vggames.meta

import vggames.shared.Game
import vggames.shared.task.Descriptions
import vggames.shared.task.Tasks
import vggames.shared.task.TaskGroup

class MetaGame (descriptions : Descriptions) extends Game {
  override val tasks = new Tasks(
      firstTaskGroup,
      secondTaskGroup
    )
  
  private def firstTaskGroup = new TaskGroup("Primeiro Grupo de Tarefas","meta.first",descriptions,
      new MetaTask("Digite o nome do seu jogo"),
      new MetaTask("Digite o nome de um outro jogo"));
 
    private def secondTaskGroup = new TaskGroup("Segundo Grupo de Tarefas","meta.second",descriptions,
      new MetaTask("Digite o nome do seu jogo"),
      new MetaTask("Digite o nome de um outro jogo"));
  
  
  
  def getDescription(): String = { null }

  def getName(): String = { "Meta" }

}