package vggames.scala

import vggames.shared.task.Descriptions
import vggames.shared.Game
import vggames.scala.specs._
import vggames.shared.task.Tasks
import vggames.shared.task.TaskGroup
import vggames.scala.specs.string.CreateString
import vggames.scala.specs.string.CreateStringTripleQuotation
import vggames.scala.specs.string.ConcatStrings
import vggames.scala.specs.string.ConcatStringWithConstant
import vggames.scala.specs.string.ReverseStrings
import vggames.scala.specs.string.StringLenght

class ScalaGame(descriptions : Descriptions) extends Game {

  override val tasks = new Tasks(
    addBasicMathExercises,
    addStringExercises,
    addAdvancedStringExercises)

  def addBasicMathExercises =
    new TaskGroup("Opera&ccedil;&otilde;es matem&aacute;ticas b&aacute;sicas", "basic.math", descriptions,
      new SomaSpec(),
      new SubSpec(),
      new MultiSpec(),
      new DivSpec())

  def addBasicBooleanExercises = {}

  def addStringExercises =
    new TaskGroup("Manipulando Strings", "basic.string.structure", descriptions,
      new CreateString(),
      new CreateStringTripleQuotation(),
      new ConcatStrings(),
      new ConcatStringWithConstant(),
      new ReverseStrings(),
      new StringLenght(),
      new ComparacaoStrings(),
      new NumberToString(),
      new AnyRefToString())

  def addAdvancedStringExercises =
    new TaskGroup("Mais manipula&ccedil;&atilde;o de Strings", "basic.string.operations", descriptions,
      new SplitStrings(),
      new SubStrings(),
      new ReplaceString(),
      new StringContains(),
      new TrimString())

  def getDescription = "Um jogo muito legal para aprender Scala"

  def getName = "Scala"

}