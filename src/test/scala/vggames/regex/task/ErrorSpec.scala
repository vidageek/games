package vggames.regex.task

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import vggames.shared.task.status.Error

@RunWith(classOf[JUnitRunner])
class ErrorSpec extends Specification {
  "the error class" should {
    "stacktraceShouldBePrintedInAnHtmlListWithClassException" in {
      new Error(new Throwable("Message :D")).getReason must_== "Message :D"
    }
  }
}
