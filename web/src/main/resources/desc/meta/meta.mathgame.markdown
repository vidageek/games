

Para criar um jogo no VidaGeek Games é necessário criar uma classe herdeira da classe `Game`.
Essa será a classe principal do jogo. Ela conterá a descrição do jogo e o nome do jogo assim como os grupos de tarefas.

<pre>package vggames.math

import vggames.shared.Game
import vggames.shared.task.Descriptions
import vggames.shared.task.Tasks
import vggames.shared.task.TaskGroup

class MathGame(descriptions : Descriptions) extends Game {
  override val tasks = new Tasks(
    sum
    )

  private def sum = new TaskGroup("Soma", "math.sum", descriptions,
    new MathTask(2, 3, "+"),
    new MathTask(4, 5, "+")
    )

  def getDescription(): String = { "Jogo onde você aprenderá a somar, subtrair e multiplicar!" }

  def getName() : String = { "Math" }

}</pre>

A trait Game contém as características comuns a todos os jogos. O valor `val tasks` 
contém os métodos que representam os grupos de tarefas (TaskGroup).
O método `getDescription` deve devolver a descrição do jogo.
O método `getName` deve devolver o nome do jogo.

Como exemplo, utilizaremos um jogo simples chamado Math contendo as operações básicas
de soma, subtração e multiplicação.


