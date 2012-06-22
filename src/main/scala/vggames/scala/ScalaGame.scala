package vggames.scala

import vggames.shared.Game
import vggames.shared.task.Descriptions
import vggames.regex.task.Tasks
import vggames.regex.task.TaskGroup
import vggames.shared.task.Task
import vggames.shared.task.status.Ok

class ScalaGame(descriptions : Descriptions) extends Game {

  override val tasks = new Tasks

  addTestTask

  def addTestTask = {
    val group = new TaskGroup("Teste", "test", descriptions)
    group.add(TestTask())
    tasks.add(group)
  }

  def getDescription = "Um jogo muito legal para aprender Scala"

  def getName = "Scala"

}

case class TestTask() extends Task {
  def judge(challenge : String) = new Ok()

  def getChallenge = "Test challenge"

}
