package vggames.shared.auth

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito
import org.specs2.specification.Scope
import org.scribe.model.Verifier
import twitter.TwitterAuthProvider

class VerifyPlayerAuthorizationSpec extends Specification with Mockito {

  "check" should {
    "sends 0k when authorized" in new VerifyPlayerAuthorizationContext{
      a.requester(any[Verifier]) returns (requester)
      all(authProvider.name, requester) returns (authProvider)

      VerifyPlayerAuthorization(authProvider.name, handleCheck, all, a).check("verifierVerified")
      there was one(handleCheck).ok(authProvider)
    }

    "sends fail when not authorized" in new VerifyPlayerAuthorizationContext {
      val authorization = VerifyPlayerAuthorization(authProvider.name, handleCheck, all, a)
      authorization.check(null)

      there was no(handleCheck).ok(any[AuthProvider])
      there was one(handleCheck).fail
    }
  }
}

trait VerifyPlayerAuthorizationContext extends Scope with Mockito with Specification {
  val handleCheck = mock[HandlerVerificationCheck]
  val a = mock[AuthenticateWithProvider]
  val requester = mock[AuthenticatedRequester]
  val authProvider = new TwitterAuthProvider(requester)
  val all = spy(new Providers())
}
