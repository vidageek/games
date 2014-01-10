package vggames.regex.task

import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import org.junit.runner.RunWith
import vggames.regex.RegexGame
import vggames.shared.task.Descriptions
import vggames.regex.RegexGame
import vggames.shared.Game

@RunWith(classOf[JUnitRunner])
class RegexGameSpec extends Specification with Mockito {
  val descriptions = mock[Descriptions]
  val answers = List("a", "abc", "\\\\", "\\$", "abcdefg12345", "AbCdEfG6", "ab\\$cd\\^Ef\\\\G1", "", " ", "\\n", "\\t", "\\f", "\\r",
    "[ab]", "[ab]d", "[a-cA-D]", "[0-2]", "[a-z]", "\\d", "\\da", "[\\da]", "\\s", "\\sa", "[\\sa]", "\\w",
    "\\wp", ".", "[^ab]", "[^abc]d", "\\D", "\\Da", "[^\\da]", "\\S", "\\Sa", "[^\\sa]", "\\W", "\\Wp",
    "a|b", "aa|bb|ab", "a?", "[a-b]?", "a+", "[a-c]+", "[a-c]*", "a*", "a{3}", "[a-c]{3}", "Nome: (.*)",
    "Nome: (\\w+) Sobrenome: (\\w+)", "((Cara)m(bolas))", "((past)a (de)) (d(ente))", "(c*)c\\1",
    "\\[([^\\]]+)\\][^\\[]+\\[/\\1\\]", "^/blog/\\d$", "^/blog$", "^/blog/", "blog$", "(?i)regex",
    "(?s).+", "17:.*", "/blog/\\d+/\\d+/\\d+/.*", "(([^ @\\.]\\.?)+)?[^\\.]@[^@]+",
    "([0-2]?\\d|3[01])/(\\d|1[0-2])/\\d+",
    "((([0-1]?\\d?\\d)|(25[0-5])|(2[0-4]\\d))\\.){3}(([0-1]?\\d?\\d)|(25[0-5])|(2[0-4]\\d))")

  "regex game" should {
    "have answers for all tasks" in {

      val game = new Game(new RegexGame(descriptions))
      game.getSize must_== answers.length

      0 until game.getSize foreach { i =>
        game.task(i).judge(answers(i)).ok aka (
          "%s task %d answer is %s".format(game.getClass().getSimpleName(), i, answers(i))) must beTrue
      }
    }

    "hasNextTask returns true if there is such task" in {
      val game = new Game(new RegexGame(descriptions))
      game.hasNextTask(0) must beTrue
    }

    "hasNextTask returns false if there is no such task" in {
      val game = new Game(new RegexGame(descriptions))
      game.hasNextTask(game.getSize + 1) must beFalse
    }

    "nextTask returns next task" in {
      val game = new Game(new RegexGame(descriptions))
      game.nextTask(3) must_== 4
    }
  }
}
