package vggames.scala

import vggames.scala.specs._
import vggames.scala.specs.booleans._
import vggames.scala.specs.ifelse._
import vggames.scala.specs.list._
import vggames.scala.specs.string._
import vggames.scala.specs.valvar._
import vggames.scala.specs.whileloop._
import vggames.shared.task.{ Descriptions, TaskGroup, Tasks }
import vggames.scala.specs.functions.BasicFunction
import vggames.scala.specs.functions.BasicFunction
import vggames.scala.specs.literal.SumTwoNumbers
import vggames.scala.specs.literal.RepresentString
import vggames.scala.specs.literal.MultiplyTwoNumbers
import vggames.shared.GameEngine

class ScalaGame extends GameEngine {

  override val tasks = new Tasks(
    addLiteralOperations,
    addValEVarExercises,
    addBasicMathExercises,
    addBasicBooleanExercises,
    addStringExercises,
    addAdvancedStringExercises,
    addConditionalExercises,
    addFunctionExercises,
    addWhileExercises,
    addListExercises)

  def addLiteralOperations =
    new TaskGroup("Bem vindo à Scala", "basic.value",
      new SumTwoNumbers(),
      new MultiplyTwoNumbers(),
      new RepresentString())

  def addValEVarExercises =
    new TaskGroup("Variáveis e Valores", "basic.varval",
      new DefineValString(),
      new DefineValInt(),
      new DefineVarString(),
      new DefineVarInt(),
      new ReassignToVar())

  def addBasicMathExercises =
    new TaskGroup("Operações matemáticas básicas", "basic.math",
      new SomaSpec(),
      new SubSpec(),
      new MultiSpec(),
      new DivSpec())

  def addBasicBooleanExercises =
    new TaskGroup("Operações Booleanas", "basic.boolean",
      new True(),
      new False(),
      new Equals(),
      new NotEquals(),
      new LessThan(),
      new MoreThan(),
      new LessOrEqual(),
      new MoreOrEqual())

  def addStringExercises =
    new TaskGroup("Manipulando Strings", "basic.string.structure",
      new CreateString(),
      new CreateStringTripleQuotation(),
      new ConcatStrings(),
      new ConcatStringWithConstant(),
      new ReverseStrings(),
      new StringLength(),
      new ComparacaoStrings(),
      new AnyRefToString())

  def addAdvancedStringExercises =
    new TaskGroup("Operações de Strings", "basic.string.operations",
      new SplitStrings(),
      new SubStrings(),
      new ReplaceString(),
      new StringContains(),
      new TrimString())

  def addFunctionExercises =
    new TaskGroup("Funções ", "basic.function",
      new BasicFunction())

  def addConditionalExercises =
    new TaskGroup("Estruturas condicionais", "basic.conditionals",
      new If(),
      new IfElse(),
      new DoubleIf(),
      new DoubleIfElse())

  def addWhileExercises =
    new TaskGroup("Estruturas de repetição while", "loop.while",
      new SomaFaixaValores(),
      new SomaArray(),
      new MenorValorArray(),
      new MaiorValorArray())

  def addListExercises =
    new TaskGroup("Manipulando Listas", "basic.list.operations",
      new IntList(),
      new StringList(),
      new FilterList())

  def description = "Um jogo muito legal para aprender Scala"

  def name = "Scala"

  def path = "scala"
}
