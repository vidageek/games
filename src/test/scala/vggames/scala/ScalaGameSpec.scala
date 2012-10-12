package vggames.scala

import org.specs2.mutable.Specification
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import vggames.shared.task.Descriptions
import vggames.shared.task.status.Ok

@RunWith(classOf[JUnitRunner])
class ScalaGameSpec extends Specification {

  "Scala Game" should {

    "have answers for all tasks" in {
      val game = new ScalaGame(new Descriptions("scala"))
      val answersSize = List("a + b", "b - a", "a * b", "a / b", "true", "false",
        "a == b", "a != b", "a < b", "a > b", "a <= b", "a >= b", "\"Minha primeira String\"",
        "\"\"\"Minha segunda String\"\"\"", "a + b", "a + \"taz\"", "a.reverse", "a.length", "a > b",
        "a.toString", "a.split(\" \")", "a.substring(2, 5)", "a.replace(\"aba\", \"ebe\")", "a.contains(\"ara\")",
        "a.trim").foldLeft(0)((i, code) => {
          game.task(i).judge(code) must_== Ok()
          i + 1
        })
      answersSize must_== game.getSize
    }
  }

}