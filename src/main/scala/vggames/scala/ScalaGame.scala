package vggames.scala

import br.com.caelum.vraptor.ioc.Component
import vggames.regex.task.{ Tasks, TaskGroup }
import vggames.scala.tasks.SimpleEval
import vggames.shared.task.Descriptions
import vggames.shared.Game

class ScalaGame(descriptions : Descriptions) extends Game {

  override val tasks = new Tasks

  addBasicTasks

  def addBasicTasks = {
    val group = new TaskGroup("Operações Básicas", "basic", descriptions)
    group.add(SimpleEval("val a = 1;\nval b = 2;", 3, "Escreva código que soma duas variáveis a e b"))
    group.add(SimpleEval("val a = 1;\nval b = 2;", -1, "Escreva código que subtrai duas variáveis a e b"))
    group.add(SimpleEval("val a = 1;\nval b = 2;", 2, "Escreva código que multiplica duas variáveis a e b"))
    group.add(SimpleEval("val a = 1;\nval b = 2;", 0, "Escreva código que divide duas variáveis a e b"))
    tasks.add(group)
  }

  def getDescription = "Um jogo muito legal para aprender Scala"

  def getName = "Scala"

}