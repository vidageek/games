package vggames.scala

import vggames.scala.specs._
import vggames.scala.specs.booleans._
import vggames.scala.specs.string._
import vggames.shared.Game
import vggames.shared.task.{ Descriptions, TaskGroup, Tasks }
import vggames.scala.specs.valvar.DefineValString
import vggames.scala.specs.valvar.DefineValInt
import vggames.scala.specs.valvar.DefineVarString
import vggames.scala.specs.valvar.DefineVarInt
import vggames.scala.specs.valvar.ReassignToVar
import vggames.scala.specs.ifelse.If

class ScalaGame(descriptions: Descriptions) extends Game {

  override val tasks = new Tasks(
    addValEVarExercises,
    addBasicMathExercises,
    addBasicBooleanExercises,
    addStringExercises,
    addAdvancedStringExercises,
    addConditionalExercises)

  def addValEVarExercises =
    new TaskGroup("Vari&aacute;veis e Valores", "basic.varval", descriptions,
      new DefineValString(),
      new DefineValInt(),
      new DefineVarString(),
      new DefineVarInt(),
      new ReassignToVar())

  def addBasicMathExercises =
    new TaskGroup("Opera&ccedil;&otilde;es matem&aacute;ticas b&aacute;sicas", "basic.math", descriptions,
      new SomaSpec(),
      new SubSpec(),
      new MultiSpec(),
      new DivSpec())

  def addBasicBooleanExercises =
    new TaskGroup("Opera&ccedil;&otilde;es Booleanas", "basic.boolean", descriptions,
      new True(),
      new False(),
      new Equals(),
      new NotEquals(),
      new LessThan(),
      new MoreThan(),
      new LessOrEqual(),
      new MoreOrEqual())

  def addStringExercises =
    new TaskGroup("Manipulando Strings", "basic.string.structure", descriptions,
      new CreateString(),
      new CreateStringTripleQuotation(),
      new ConcatStrings(),
      new ConcatStringWithConstant(),
      new ReverseStrings(),
      new StringLength(),
      new ComparacaoStrings(),
      new AnyRefToString())

  def addAdvancedStringExercises =
    new TaskGroup("Opera&ccedil;&otilde;es de Strings", "basic.string.operations", descriptions,
      new SplitStrings(),
      new SubStrings(),
      new ReplaceString(),
      new StringContains(),
      new TrimString())

  def addConditionalExercises =
    new TaskGroup("Estruturas condicionais", "basic.conditionals", descriptions,
      new If())

  def getDescription = "Um jogo muito legal para aprender Scala"

  def getName = "Scala"

}