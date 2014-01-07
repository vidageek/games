package vggames.shared.vraptor

import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import org.specs2.mutable.Specification
import org.specs2.mock.Mockito
import javax.servlet.http.HttpServletRequest

@RunWith(classOf[JUnitRunner])
class RefererSpec extends Specification with Mockito {

  "referer" should {
    "return the referer from the header" in {
      val req = mock[HttpServletRequest]
      req.getHeader("Referer") returns "http://bla.com/asdf"
      req.getServerName returns "bla.com"

      new Referer(req).url must_== "http://bla.com/asdf"
    }
    "return the base url if no header can be found" in {
      val req = mock[HttpServletRequest]
      req.getHeader("Referer") returns null
      req.getServerName returns "bla.com"

      new Referer(req).url must_== "http://bla.com"
    }
    "return the base url if referer header is not from the same domain" in {
      val req = mock[HttpServletRequest]
      req.getHeader("Referer") returns "http://google.com/asdf"
      req.getServerName returns "bla.com"

      new Referer(req).url must_== "http://bla.com"
    }
  }

}