package vggames.scala

import org.specs2.mutable.Specification
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import vggames.shared.task.Descriptions
import vggames.shared.task.status.Ok

@RunWith(classOf[JUnitRunner])
class ScalaGameSpec extends Specification with Answers {

  val answers = valvar ++ matematica ++ booleana ++ stringStructure ++ stringOperations ++ ifelse ++ function

  "Scala Game" should {

    "not have a task vulnerable to evil code submission" in {
      val game = new ScalaGame(new Descriptions("scala"))
      val answersSize = answers.foldLeft(0) { (i, answer) =>
        val fail = game.task(i).judge("System.setSecurityManager(null);\n%s".format(answer))
        fail.reason must contain("Tentativa de executar c&oacute;digo privilegiado dentro de uma task.")
        i + 1
      }
      answersSize must_== game.getSize
    }

    "have answers for all tasks" in {
      val game = new ScalaGame(new Descriptions("scala"))
      val answersSize = answers.foldLeft(0)((i, code) => {
        game.task(i).judge(code) must_== Ok()
        i + 1
      })
      answersSize must_== game.getSize
    }
  }
}

trait Answers {
  def valvar = List("val valor = \"val\"", "val numero = 123", "var valor = \"var\"", "var numero = 314",
    "var rio = \"tietê\"\n rio = \"pinheiros\"")

  def matematica = List("a + b", "b - a", "a * b", "a / b")
  def booleana = List("true", "false", "a == b", "a != b", "a < b", "a > b", "a <= b", "a >= b")
  def stringStructure = List("\"Minha primeira String\"", "\"\"\"Minha segunda String\"\"\"", "a + b",
    "a + \"taz\"", "a.reverse", "a.length", "a > b", "a.toString")
  def stringOperations = List("a.split(\" \")", "a.substring(2, 5)", "a.replace(\"aba\", \"ebe\")",
    "a.contains(\"ara\")", "a.trim")
  def ifelse = List("""if (numero < 0) "negativo"""",
    """if (numero < 0) "negativo" else "positivo"""",
    """if (numero < 0) "negativo" else if (numero > 0) "positivo"""",
    """if (numero < 0) "negativo" else if (numero > 0) "positivo" else "neutro"""")
  def function = List("""(x: Int) => { x+1 }""")
}