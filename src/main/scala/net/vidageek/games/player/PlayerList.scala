package net.vidageek.games.player
import br.com.caelum.vraptor.ioc.Component
import br.com.caelum.vraptor.ioc.SessionScoped
import org.prevayler.PrevaylerFactory
import scala.collection.mutable.ListBuffer

@Component
class PlayerList {

  val players: ListBuffer[Player] = ListBuffer[Player]()

  def append(player: Player) = {
    val prevayler = PrevaylerFactory.createPrevayler(players)
    players += player
  }

  def get(name : String): Option[Player] = {
    players.find(p => p.name == name)
  }
}
