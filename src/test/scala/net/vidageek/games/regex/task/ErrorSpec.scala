package net.vidageek.games.regex.task

import net.vidageek.games.task.status.Error
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ErrorSpec extends Specification {
  "the error class" should {
    "stacktraceShouldBePrintedInAnHtmlListWithClassException" in {
      new Error(new Throwable("Message :D")).getReason() must_== "Message :D"
    }
  }
}
