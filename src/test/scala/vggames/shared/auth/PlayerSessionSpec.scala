package vggames.shared.auth

import org.specs2.mutable.Specification
import vggames.shared.player.Player
import org.specs2.mock.Mockito
import javax.servlet.http.HttpSession
import org.specs2.specification.Scope

class PlayerSessionSpec extends Specification with Mockito {

  "add" should {
    "includes player to session" in new PlayerSessionContext {
      val player = mock[Player]
      playerSession.add(player)
      there was one(session).setAttribute("player", player)
    }
  }

  "remove" should {
    "removes a player from session" in new PlayerSessionContext {
      playerSession.remove
      there was one(session).removeAttribute("player")
    }
  }

  "get" should {
    "existent user into session" in new PlayerSessionContext {
      val player = mock[Player]
      session.getAttribute("player") returns player

      playerSession.get must_== player
    }
  }
}

trait PlayerSessionContext extends Scope with Mockito with Specification {
  val session = mock[HttpSession]
  val playerSession = new PlayerSession(session)
}