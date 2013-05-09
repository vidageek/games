package vggames.math

import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import org.junit.runner.RunWith
import vggames.shared.task.Descriptions
import vggames.browser.WebBrowser

@RunWith(classOf[JUnitRunner])
class MathGameSpec extends Specification with Mockito with WebBrowser{
  val descriptions = mock[Descriptions]
  val answers = List(5, 9, 1, 36, -1, 3, 1, 12, 6, 20, 0, 121).map(_.toString )

  "math game" should {
    "have answers for all tasks" in {

      val game = new MathGame(descriptions)
      game.getSize must_== answers.length

      0 until game.getSize foreach { i =>
        game.task(i).judge(answers(i)).ok aka (
          "%s task %d answer is %s".format(game.getClass().getSimpleName(), i, answers(i))) must beTrue
      }
    }
    /*
    "selenium test" in {
      task("math",1);
      solve
    }*/
  }
}