package vggames.meta

import vggames.shared.Game
import vggames.shared.task.Descriptions
import vggames.shared.task.Tasks
import vggames.shared.task.TaskGroup

class MetaGame (descriptions : Descriptions) extends Game {
  override val tasks = new Tasks(
      firstTaskGroup,
      secondTaskGroup,
      test
    )

  private def firstTaskGroup = new TaskGroup("Primeiro Grupo de Tarefas", "meta.first", descriptions,
    new MetaTask("Digite o nome do seu jogo"),
    new MetaTask("Digite o nome de um outro jogo"));

  private def secondTaskGroup = new TaskGroup("Criando a classe do seu jogo", "meta.second", descriptions,
    new MetaTask("Crie uma classe chamada MathGame que estende a classe Game"),
    new MetaTask("Inclua o nome MathGame no método getName"),
    new MetaTask("Inclua a descrição do jogo no método getDescription"),
    new MetaTask("Inclua o método soma na val tasks")
  );
  
  private def test = new TaskGroup("Criação de testes para um novo game", "meta.test", descriptions,
      new MetaTask("Seguindo o exemplo dos testes existentes para o MathGame crie uma classe de teste para seu jogo.")
      )
  
  def getDescription(): String = { null }

  def getName(): String = { "Meta" }

}