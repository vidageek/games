package vggames.meta

import vggames.shared.Game
import vggames.shared.task.Descriptions
import vggames.shared.task.Tasks
import vggames.shared.task.TaskGroup

class MetaGame (descriptions : Descriptions) extends Game {
  override val tasks = new Tasks(
      configTaskGroup,
      secondTaskGroup,
      registerFactoryGroup,
      createJSPGroup,
      test,
      testp2
    )


  private def configTaskGroup = new TaskGroup("Configurando o ambiente", "meta.config", descriptions,
    new MetaTask("Siga os passos ao lado para configurar o ambiente"),
    new MetaTask("Digite o nome de um outro jogo"));

  private def secondTaskGroup = new TaskGroup("Segundo Grupo de Tarefas", "meta.second", descriptions,
    new MetaTask("Digite o nome do seu jogo"),
    new MetaTask("Digite o nome de um outro jogo"))
  
  private def registerFactoryGroup = new TaskGroup("Registro do jogo na classe factory", "meta.register", descriptions,   
    new MetaTask("Procurar a classe GameFactory.scala"),
  	new MetaTask("Registrar sua classe na factory"))
  
  private def createJSPGroup=new TaskGroup("Criar um JSP file para o jogo","meta.jsp",descriptions,
	new MetaTask("Crie um arquivo jsp para o jogo"),
	new MetaTask("Modifique o template mostrado na descrição para seu jogo"))
  
  private def test = new TaskGroup("Criação de testes para um novo game", "meta.test", descriptions,
      new MetaTask("Seguindo o exemplo dos testes existentes para o MathGame.scala crie uma classe de teste para seu jogo.")
      )
    private def testp2 = new TaskGroup("Segunda parte da criação de testes para um novo game", "meta.testp2", descriptions,
      new MetaTask("Seguindo o exemplo dos testes existentes para o MathTask.scala crie uma classe de teste para seu jogo.")
      )
  
  
  def getDescription(): String = { null }
 
  def getName(): String = { "Meta" }

}