package vggames.meta

import vggames.shared.Game
import vggames.shared.task.Descriptions
import vggames.shared.task.Tasks
import vggames.shared.task.TaskGroup

class MetaGame (descriptions : Descriptions) extends Game {
  override val tasks = new Tasks(
      firstTaskGroup,
      secondTaskGroup,
      registerFactoryGroup,
      test
    )

  private def firstTaskGroup = new TaskGroup("Primeiro Grupo de Tarefas", "meta.first", descriptions,
    new MetaTask("Digite o nome do seu jogo"),
    new MetaTask("Digite o nome de um outro jogo"))

  private def secondTaskGroup = new TaskGroup("Segundo Grupo de Tarefas", "meta.second", descriptions,
    new MetaTask("Digite o nome do seu jogo"),
    new MetaTask("Digite o nome de um outro jogo"))
  
  private def registerFactoryGroup = new TaskGroup("Registro do jogo na classe factory", "meta.register", descriptions,   
    new MetaTask("Procurar a classe GameFactory.scala"),
  	new MetaTask("Registrar sua classe na factory")  
  )
  
  private def test = new TaskGroup("Criação de testes para um novo game", "meta.test", descriptions,
      new MetaTask("Seguindo o exemplo dos testes existentes para o MathGame crie uma classe de teste para seu jogo.")
      )
  
  def getDescription(): String = { null }

  def getName(): String = { "Meta" }

}