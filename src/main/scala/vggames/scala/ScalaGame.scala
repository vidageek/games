package vggames.scala

import vggames.shared.task.Descriptions
import vggames.shared.Game
import vggames.scala.code.Specs2Eval
import vggames.scala.specs._
import vggames.shared.task.Tasks
import vggames.shared.task.TaskGroup

class ScalaGame(descriptions : Descriptions) extends Game {

  override val tasks = new Tasks(addBasicMathExercises)

  def addBasicMathExercises = {
    val group = new TaskGroup("Opera&ccedil;&otilde;es matem&aacute;ticas b&aacute;sicas", "basic.math", descriptions)
    group.add(Specs2Eval(new SomaSpec()))
    group.add(Specs2Eval(new SubSpec()))
    group.add(Specs2Eval(new MultiSpec()))
    group.add(Specs2Eval(new DivSpec()))
    group
  }

  def getDescription = "Um jogo muito legal para aprender Scala"

  def getName = "Scala"

}