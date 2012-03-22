package net.vidageek.games.auth

import net.vidageek.games.auth.twitter.TwitterAuthProvider
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class ProvidersSpec extends Specification with Mockito {
  val twitterAuthPovider = mock[TwitterAuthProvider]
  val aTestAuthProvider = mock[AuthProvider]

  twitterAuthPovider.name returns "twitter"
  aTestAuthProvider.name returns "TestProvider"

  val providers = new Providers(List(twitterAuthPovider, aTestAuthProvider))

  "providers" should {
    "have the twitter provider" in {
      providers.quantity must_== 2
      providers.byName("twitter") must_== twitterAuthPovider
      providers.byName("TestProvider") must_== aTestAuthProvider
    }

    "find the twitter provider" in {
      providers.byName(twitterAuthPovider.name) must_== twitterAuthPovider
    }
  }
}
