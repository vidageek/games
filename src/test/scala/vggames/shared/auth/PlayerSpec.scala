package vggames.shared.auth

import org.specs2.mock.Mockito
import org.specs2.mutable.Specification

import vggames.shared.player.Player
import org.specs2.specification.Scope

class PlayerSpec extends Specification with Mockito {
  "The logout" should {
    "unauthorize the player" in new PlayerContext {
      val player = aPlayer
      player.logout
      there was one(authProvider).logout
    }
  }
}

trait PlayerContext extends Scope with Specification with Mockito {
  val authProvider = mock[AuthProvider]
  val aPlayer = new Player(authProvider)
}
