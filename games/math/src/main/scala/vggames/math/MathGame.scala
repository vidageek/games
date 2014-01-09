package vggames.math

import vggames.shared.task.Descriptions
import vggames.shared.task.Tasks
import vggames.shared.task.TaskGroup
import vggames.shared.GameEngine

class MathGame(descriptions : Descriptions) extends GameEngine {
  override val tasks = new Tasks(
    sum,
    subtraction,
    multi)

  private def sum = new TaskGroup("Soma", "math.sum", descriptions,
    new MathTask(2, 3, "+"),
    new MathTask(4, 5, "+"),
    new MathTask(1, 0, "+"),
    new MathTask(24, 12, "+"))

  private def subtraction = new TaskGroup("Subtração", "math.subtraction", descriptions,
    new MathTask(2, 3, "-"),
    new MathTask(8, 5, "-"),
    new MathTask(1, 0, "-"),
    new MathTask(24, 12, "-"))

  private def multi = new TaskGroup("Multiplicação", "math.multi", descriptions,
    new MathTask(2, 3, "*"),
    new MathTask(4, 5, "*"),
    new MathTask(1, 0, "*"),
    new MathTask(11, 11, "*"))

  def getDescription() : String = { "Jogo onde você aprenderá a somar, subtrair e multiplicar!" }

  def getName() : String = { "Math" }

}