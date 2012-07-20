package vggames.scala

import vggames.regex.task.{ Tasks, TaskGroup }
import vggames.scala.specs.SomaSpec
import vggames.shared.task.Descriptions
import vggames.shared.Game
import vggames.scala.code.Specs2Eval

class ScalaGame(descriptions : Descriptions) extends Game {

  override val tasks = new Tasks

  addBasicMathExercises

  def addBasicMathExercises = {
    val group = new TaskGroup("Opera&ccedil;&otilde;es matem&aacute;ticas b&aacute;sicas", "basic.math", descriptions)
    group.add(Specs2Eval(new SomaSpec()))
    tasks.add(group)
  }

  def getDescription = "Um jogo muito legal para aprender Scala"

  def getName = "Scala"

}