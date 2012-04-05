package net.vidageek.games.regex.task

import net.vidageek.games.task._
import net.vidageek.games.task.status.Error
import net.vidageek.games.regex.task.MatcherTargets._
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class IndexedTaskSpec extends Specification {
  "an IndexedTask" should {
    "return judged task with error when invalid regex" in {
      new IndexedTask(
        new GroupedTask(new TaskGroup("b", "", null), new Match(from("a"))), 1
      ).judge("aIncalidRegex)") must beAnInstanceOf[Error]
    }
  }
}
