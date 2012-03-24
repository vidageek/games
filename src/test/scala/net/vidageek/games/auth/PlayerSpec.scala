package net.vidageek.games.auth

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito

class PlayerSpec extends Specification with Mockito {
  "authorized " should {
    val authorizationVerifier = mock[AuthorizationVerifier]
    authorizationVerifier.authorized returns true
    "when user aquire a authentication provider" in {
      val player = new Player()
      player.provider = mock[AuthProvider]
      player.authorize(authorizationVerifier)
      player.getAuthorized must beTrue
    }
  }
}
