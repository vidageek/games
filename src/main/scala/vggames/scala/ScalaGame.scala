package vggames.scala

import vggames.shared.task.Descriptions
import vggames.shared.Game
import vggames.scala.code.Specs2Eval
import vggames.scala.specs._
import vggames.shared.task.Tasks
import vggames.shared.task.TaskGroup

class ScalaGame(descriptions : Descriptions) extends Game {

  override val tasks = new Tasks(addBasicMathExercises)

  def addBasicMathExercises =
    new TaskGroup("Opera&ccedil;&otilde;es matem&aacute;ticas b&aacute;sicas", "basic.math", descriptions,
      Specs2Eval(new SomaSpec()),
      Specs2Eval(new SubSpec()),
      Specs2Eval(new MultiSpec()),
      Specs2Eval(new DivSpec()))

  def getDescription = "Um jogo muito legal para aprender Scala"

  def getName = "Scala"

}