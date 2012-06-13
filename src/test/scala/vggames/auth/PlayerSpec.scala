package vggames.auth

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito
import vggames.player.Player
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PlayerSpec extends Specification with Mockito {
  def aPlayer = {
    val player = new Player("asdrubal")
    player.provider = mock[AuthProvider]
    player
  }
  "The getAuthorized" should {

    val authorizationVerifier = mock[AuthorizationVerifier]

    "authorized when player aquire a authentication verifier" in {
      val player = aPlayer
      authorizationVerifier.authorized returns true
      player.authorize(authorizationVerifier)
      player.getAuthorized must beTrue
    }

    "unauthorized when player aquire unauthorized authentication verifier" in {
      val player = aPlayer
      authorizationVerifier.authorized returns false
      player.authorize(authorizationVerifier)
      player.getAuthorized must beFalse
    }
  }

  "The logout" should {
    "unauthorize the player" in {
      val player = aPlayer
      player.logout
      player.getAuthorized must beFalse
    }
  }
}
