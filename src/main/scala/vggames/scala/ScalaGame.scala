package vggames.scala

import vggames.scala.specs._
import vggames.scala.specs.booleans._
import vggames.scala.specs.ifelse._
import vggames.scala.specs.list._
import vggames.scala.specs.string._
import vggames.scala.specs.valvar._
import vggames.scala.specs.whileloop._
import vggames.shared.Game
import vggames.shared.task.{ Descriptions, TaskGroup, Tasks }
import vggames.scala.specs.functions.BasicFunction
import vggames.scala.specs.functions.BasicFunction

class ScalaGame(descriptions : Descriptions) extends Game {

  override val tasks = new Tasks(
    addValEVarExercises,
    addBasicMathExercises,
    addBasicBooleanExercises,
    addStringExercises,
    addAdvancedStringExercises,
    addConditionalExercises,
    addFunctionExercises,
    addWhileExercises,
    addListExercises)

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

  def addFunctionExercises =
    new TaskGroup("Fun&ccedil;&otilde;es ", "basic.function", descriptions,
      new BasicFunction())

  def addConditionalExercises =
    new TaskGroup("Estruturas condicionais", "basic.conditionals", descriptions,
      new If(),
      new IfElse(),
      new DoubleIf(),
      new DoubleIfElse())

  def addWhileExercises =
    new TaskGroup("Estruturas de repeti&ccedil;&atilde;o while", "loop.while", descriptions,
      new SomaArray())

  def addListExercises = 
    new TaskGroup("Manipulando Listas", "basic.list.operations", descriptions,
        new IntList(),
        new StringList(),
        new FilterList())
  
  def getDescription = "Um jogo muito legal para aprender Scala"

  def getName = "Scala"
}
