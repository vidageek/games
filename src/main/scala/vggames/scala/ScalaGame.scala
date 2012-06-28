package vggames.scala

import vggames.regex.task.{Tasks, TaskGroup}
import vggames.scala.tasks.Sum
import vggames.shared.task.Descriptions
import vggames.shared.Game

class ScalaGame(descriptions : Descriptions) extends Game {

  override val tasks = new Tasks

  addBasicTasks

  def addBasicTasks = {
    val group = new TaskGroup("Exemplo", "example", descriptions)
    group.add(new Sum)

    tasks.add(group)
  }

  def getDescription = "Um jogo muito legal para aprender Scala"

  def getName = "Scala"

}