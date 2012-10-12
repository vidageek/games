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
      val becauseSpecsForcesMeTo = List("a + b", "b - a", "a * b", "a / b").foldLeft(0)((i, code) => {
        game.task(i).judge(code) must_== Ok()
        i + 1
      })
      becauseSpecsForcesMeTo must_== game.getSize
    }
  }

}