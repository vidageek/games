package vggames.shared.vraptor

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import org.specs2.specification.Scope
import org.mockito.Mockito.withSettings
import org.mockito.Answers.RETURNS_DEEP_STUBS
import javax.servlet.http.HttpServletRequest

@RunWith(classOf[JUnitRunner])
class RequestDataSpec extends Specification with Mockito {

  "Request Data" should {
    "identify which game is being played" in new RequestDataScope("/play/regex") {
      "regex" must_== new RequestData(request).game
    }

    "identify which game is being played if it ends with /" in new RequestDataScope("/play/regex/") {
      "regex" must_== new RequestData(request).game
    }

    "identify which game is being played if it has something beyond /" in new RequestDataScope("/play/regex/asdrubal") {
      "regex" must_== new RequestData(request).game
    }
  }

  class RequestDataScope(uri : String) extends Scope {
    val request = mock[HttpServletRequest](withSettings.defaultAnswer(RETURNS_DEEP_STUBS.get))
    request.getRequestURI returns uri
  }
}