package vggames

import br.com.caelum.vraptor.Result
import vggames.task.status.{ Failed, Ok }
import org.mockito.Mockito.withSettings
import org.mockito.Answers.RETURNS_DEEP_STUBS
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import org.specs2.specification.Scope
import org.specs2.runner.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class GameConsoleSpec extends Specification with Mockito {
  "a game console" should {
    "redirect to same task when task is incorrect" in new GameConsoleScope {
      val judgedTask = new Failed("asdf")
      game.task(3).judge("challenge") returns judgedTask

      new GameConsole(result, game).submit("name", 3, "challenge")

      there was one(gameConsole).task("name", 3)
      there was one(result).include("judgedTask", judgedTask)
      there was one(result).include("challenge", "challenge")
    }

    "redirect to game begin if there are no more tasks" in new GameConsoleScope {
      val judgedTask = new Ok()
      game.task(3).judge("challenge") returns judgedTask
      game.hasNextTask(any[Int]) returns false

      new GameConsole(result, game).submit("name", 3, "challenge")

      there was one(gameConsole).index("name")
      there was one(result).include("judgedTask", judgedTask)
    }

    "redirect to next task if task is ok" in new GameConsoleScope {
      val judgedTask = new Ok()
      game.task(3).judge("challenge") returns judgedTask
      game.hasNextTask(any[Int]) returns true
      game.nextTask(any[Int]) returns 4

      new GameConsole(result, game).submit("name", 3, "challenge")

      there was one(gameConsole).task("name", 4)
      there was one(result).include("judgedTask", judgedTask)
    }
  }

  trait GameConsoleScope extends Scope {
    val result = mock[Result]
    val game = mock[Game](withSettings.defaultAnswer(RETURNS_DEEP_STUBS.get))
    val gameConsole = mock[GameConsole]

    result.redirectTo(any[GameConsole]) returns gameConsole
  }
}
