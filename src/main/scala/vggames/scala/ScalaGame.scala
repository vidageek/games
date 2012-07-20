package vggames.scala

import vggames.regex.task.{ Tasks, TaskGroup }
import vggames.scala.specs.ExampleSpec
import vggames.shared.task.Descriptions
import vggames.shared.Game
import vggames.scala.code.Specs2Eval

class ScalaGame(descriptions : Descriptions) extends Game {

  override val tasks = new Tasks

  addSpecs2Tasks

  def addSpecs2Tasks = {
    val group = new TaskGroup("Test", "basic", descriptions)
    group.add(Specs2Eval(new ExampleSpec()))
    tasks.add(group)
  }

  def getDescription = "Um jogo muito legal para aprender Scala"

  def getName = "Scala"

}