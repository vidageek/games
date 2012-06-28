package vggames.scala.tasks

import vggames.shared.task.Task
import vggames.shared.task.JudgedTask
import vggames.shared.task.status.Ok
import vggames.scala.ScalaProcessor
import vggames.shared.task.status.Failed
import com.twitter.util.Eval.CompilerException

class Sum extends Task {

  def judge(challenge : String) : JudgedTask = {
    try {
      val resposta = (new ScalaProcessor).processCode[Int]("val a = 1;\nval b = 2;\n" + challenge)
      if (resposta == 3)
        Ok()
      else
        Failed("Não somou dois números")
    } catch {
      case e : CompilerException => Failed("Falha de compilação: " + e.getMessage)
      case e => Failed("Exception foi lançada durante execução: " + e.getMessage)
    }
  }

  def getChallenge : String = "Escreva código que soma duas variáveis a e b"
}