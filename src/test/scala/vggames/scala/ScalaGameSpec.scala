package vggames.scala

import org.specs2.mutable.Specification
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import vggames.shared.task.Descriptions
import vggames.shared.task.status.Ok

@RunWith(classOf[JUnitRunner])
class ScalaGameSpec extends Specification {

  "Scala Game" should {
    "not have a task vulnerable to evil code submission" in {
      val game = new ScalaGame(new Descriptions("scala"))
      (0 to (game.getSize - 1)).toList.foreach { i =>
        val fail = game.task(i).judge("""System.setSecurityManager(null);1""")
        fail.getReason must contain("Tentativa de executar c&oacute;digo privilegiado dentro de uma task.")
      }
    }

    "have answers for all tasks" in {
      val game = new ScalaGame(new Descriptions("scala"))
      val becauseSpecsToldMeSo = List("a + b").foldLeft(0)((i, code) => {
        game.task(i).judge(code) must_== Ok()
        i + 1
      })
      becauseSpecsToldMeSo must_== game.getSize
    }
  }

}