package vggames.shared.auth

import javax.servlet.http.HttpSession
import br.com.caelum.vraptor.ioc.Component
import vggames.shared.player.Player

@Component
class PlayerSession(session: HttpSession) {

  def add(a: Player) = session.setAttribute(name, a)

  def remove = session.removeAttribute(name)

  def get:Player = session.getAttribute(name).asInstanceOf[Player]


  private def name = "player"
}
