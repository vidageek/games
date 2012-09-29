package vggames.scala

import vggames.shared.task.Descriptions

import vggames.shared.Game
import vggames.scala.specs._
import vggames.shared.task.Tasks
import vggames.shared.task.TaskGroup

class ScalaGame(descriptions : Descriptions) extends Game {

  override val tasks = new Tasks(addBasicMathExercises)

  def addBasicMathExercises =
    new TaskGroup("Opera&ccedil;&otilde;es matem&aacute;ticas b&aacute;sicas", "basic.math", descriptions,
      new SomaSpec(),
      new SubSpec(),
      new MultiSpec(),
      new DivSpec())

  def getDescription = "Um jogo muito legal para aprender Scala"

  def getName = "Scala"

}