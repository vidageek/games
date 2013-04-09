

Para criar um jogo no VidaGeek Games é necessário criar uma classe herdeira da classe `Game`.
Essa será a classe principal do jogo. Ela conterá a descrição do jogo e o nome do jogo assim como os grupos de tarefas.

class NomeDoJogo(descriptions : Descriptions) extends Game {

  override val tasks = new Tasks(
    grupo1,
    grupo2,
    ..)
    
  private def grupo1 = new TaskGroup("Grupo de tarefas 1", "jogo.primeiro", descriptions,
    new Tarefa(1),
    new Tarefa(2))
    
    ..

  def getDescription() : String = { "Essa é a descrição do jogo." }

  def getName() : String = { "NomeDoJogo" }

}

A trait Game contém as características comuns a todos os jogos. O valor `val tasks` 
contém os métodos que representam os grupos de tarefas (TaskGroup).
O método `getDescription` deve devolver a descrição do jogo.
O método `getName` deve devolver o nome do jogo.

Como exemplo, utilizaremos um jogo simples chamado Math contendo as operações básicas
de soma, subtração e multiplicação.


