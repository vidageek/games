package net.vidageek.games.auth

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito

class PlayerSpec extends Specification with Mockito {
  "The getAuthorized " should {
    val player = new Player()
    player.provider = mock[AuthProvider]
    val authorizationVerifier = mock[AuthorizationVerifier]

    "be true when player aquire a authentication verifier" in {
      authorizationVerifier.authorized returns true
      player.authorize(authorizationVerifier)
      player.getAuthorized must beTrue
    }

    "be false when player aquire unauthorized authorization verifier" in {
      authorizationVerifier.authorized returns false
      player.authorize(authorizationVerifier)
      player.getAuthorized must beFalse
    }
  }
}
