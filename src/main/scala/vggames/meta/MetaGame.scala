package vggames.meta

import vggames.shared.Game
import vggames.shared.task.Descriptions
import vggames.shared.task.Tasks
import vggames.shared.task.TaskGroup

class MetaGame (descriptions : Descriptions) extends Game {
  override val tasks = new Tasks(
      configTaskGroup,
      mathgameTaskGroup,
      mathtaskTaskGroup,
      markdownTaskGroup,
      registerFactoryGroup,
      createJSPGroup,
      test,
      testp2
    )


  private def configTaskGroup = new TaskGroup("Configurando o ambiente", "meta.config", descriptions,
    new MetaTask("Siga os passos ao lado para configurar o ambiente"));

  private def mathgameTaskGroup = new TaskGroup("Criando a classe do seu jogo", "meta.mathgame", descriptions,
    new MetaTask("Crie uma classe chamada MathGame que estende a classe Game"),
    new MetaTask("Inclua o nome Math no método getName, este será o nome de exibição do jogo"),
    new MetaTask("Inclua uma descrição para seu jogo no método getDescription()"),
    new MetaTask("Crie um método chamado sum que será um novo TaskGroup e responsável por agrupar diferentes tarefas sobre uma mesma referência"),
    new MetaTask("Inclua o método sum em val tasks, isso fará com que esse grupo de tarefas seja apresentado ao usuário"),
    new MetaTask("Crie novas Taks dentro de sum, cada Task será uma nova tarefa")
  );
  
  private def mathtaskTaskGroup = new TaskGroup("Criando a classe que julga as respostas de uma tarefa", "meta.mathtask", descriptions,
      new MetaTask("Crie uma nova classe chamada MathTask que será responsável por julgar a entrada do usuário")
      );
  
  private def markdownTaskGroup = new TaskGroup("Referências", "meta.markdown", descriptions,
      new MetaTask("Criando um arquivo de referências."));
  
  
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