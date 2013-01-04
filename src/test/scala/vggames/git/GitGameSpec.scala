package vggames.git

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito
import vggames.shared.task.Descriptions
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class GitGameSpec extends Specification with Mockito {

  val descriptions = mock[Descriptions]
  val answers = Vector("git init repositorio", "git init repo2")

  "git game" should {
    "have answers for all games" in {
      val game = new GitGame(descriptions)

      answers.size must_== game.getSize

      0 until game.getSize foreach { i =>
        game.task(i).judge(answers(i)).ok aka (
          "%s task %d answer is %s".format(game.getClass().getSimpleName(), i, answers(i))) must beTrue
      }
    }
  }

}