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
class DescriptionsFactorySpec extends Specification with Mockito {

  "Description Factory" should {
    "returns the same instance for the same game" in {
      val data = mock[RequestData]
      data.game returns "regex"

      val factory = new DescriptionsFactory(data)
      factory.getInstance must be(factory.getInstance)
      factory.getInstance must be(factory.getInstance)
      factory.getInstance must be(factory.getInstance)
    }
  }

}