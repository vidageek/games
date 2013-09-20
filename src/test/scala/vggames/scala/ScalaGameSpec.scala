package vggames.scala

import org.specs2.mutable.Specification
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import vggames.shared.task.Descriptions
import vggames.shared.task.status.Ok

@RunWith(classOf[JUnitRunner])
class ScalaGameSpec extends Specification {
  import Answers._

  val answers = literals ++ valvar ++ matematica ++ booleana ++ stringStructure ++ stringOperations ++
    ifelse ++ function ++ whileloop ++ list

  "Scala Game" should {
    val game = new ScalaGame(new Descriptions("scala"))

    "have the correct number of answers" in {
      answers.size must_== game.getSize
    }

    "not have a task vulnerable to evil code submission" in {
      val answersSize = answers.zipWithIndex.map {
        case (code, i) =>
          val fail = game.task(i).judge("System.setSecurityManager(null);\n%s".format(code))
          fail.reason must contain("Tentativa de executar c&oacute;digo privilegiado dentro de uma task.")
      }
    }

    "have answers for all tasks" in {
      answers.zipWithIndex.map {
        case (code, i) =>
          game.task(i).judge(code) must_== Ok()
      }
    }
  }

  object Answers {
    def literals = List("1 + 2", "2 * 3", """"jogo legal!"""")

    def valvar = List("val valor = \"val\"", "val numero = 123", "var valor = \"var\"", "var numero = 314",
      "var rio = \"tietê\"\n rio = \"pinheiros\"")

    def matematica = List("a + b", "b - a", "a * b", "a / b")

    def booleana = List("true", "false", "a == b", "a != b", "a < b", "a > b", "a <= b", "a >= b")

    def stringStructure = List("\"Minha primeira String\"", "\"\"\"Minha segunda String\"\"\"", "a + b",
      "a + \"taz\"", "a.reverse", "a.length", "a > b", "a.toString")

    def stringOperations = List("a.split(\" \")", "a.substring(2, 5)", "a.replace(\"aba\", \"ebe\")",
      "a.contains(\"ara\")", "a.trim")

    def ifelse = List(
      """if (numero < 0) "negativo" """,
      """if (numero < 0) "negativo" else "positivo" """,
      """if (numero < 0) "negativo" else if (numero > 0) "positivo" """,
      """if (numero < 0) "negativo" else if (numero > 0) "positivo" else "neutro" """)

    def function = List("(x: Int) => { x+1 }")

    def whileloop = List(
      "var soma = 0; var i = 0; while(i <= 100) {soma += i; i += 1}",
      "var soma = 0; var i = 0; while(i < arr.size) {soma += arr(i); i += 1}",
      "var menor = arr(0); var i = 1; while(i < arr.size) {if (arr(i) < menor) menor = arr(i); i += 1}",
      "var maior = arr(0); var i = 1; while(i < arr.size) {if (arr(i) > maior) maior = arr(i); i += 1}")

    def list = List(
      "List(1,2,3)",
      """List("abacaxi","laranja","abacate")""",
      "lista.filter(_ < 4)")
  }
}
