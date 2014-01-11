package vggames.math;

import vggames.shared.task.JudgedTask
import vggames.shared.task.Task
import vggames.shared.task.status.Ok
import vggames.shared.task.status.Failed

case class MathTask(a : Int, b : Int, op : String) extends Task {

  def judge(challenge : String) : JudgedTask = eval(challenge);

  def getChallenge : String = s"$a$op$b"

  def resource = ""

  private def eval(challenge : String) : JudgedTask =
    {
      val intChallenge : Int = challenge.toInt;
      op match {
        case "+" => if (a + b == intChallenge) new Ok() else new Failed("Resposta errada, tente novamente!");
        case "-" => if (a - b == intChallenge) new Ok() else new Failed("Resposta errada, tente novamente!");
        case "*" => if (a * b == intChallenge) new Ok() else new Failed("Resposta errada, tente novamente!");
      }
    }

}