package vggames.shared.auth

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner


@RunWith(classOf[JUnitRunner])
class AuthorizationVerifierSpec extends Specification {

  "The authorized" should {
    "authorize with valid verifier" in {
      AuthorizationVerifier("validVerifier").authorized must beTrue
    }

    "not authorize" in {
      AuthorizationVerifier(null).authorized must beFalse
    }
  }
}
