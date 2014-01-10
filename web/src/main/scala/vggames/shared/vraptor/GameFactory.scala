package vggames.shared.vraptor

import br.com.caelum.vraptor.ioc.Component
import br.com.caelum.vraptor.ioc.ComponentFactory
import br.com.caelum.vraptor.ioc.Component
import br.com.caelum.vraptor.ioc.ApplicationScoped
import vggames.shared.Game
import java.util.ArrayList
import vggames.shared.GameEngine
import scala.collection.JavaConverters._
import vggames.git.GitGame

@Component
class GameFactory(cached : GameFactoryCache, data : RequestData) extends ComponentFactory[Game] {

  def getInstance : Game = cached(data.game).getOrElse(
    throw new RuntimeException("Não foi possível criar o jogo [" + data.game + "]. Talvez " +
      "seja necessário registrá-lo na GameFactory"))
}

@Component
@ApplicationScoped
class GameFactoryCache(cache : DescriptionsCache, gameList : java.util.List[GameEngine]) {

  println(s"=============================$gameList")

  val games = Map[String, Game](gameList.asScala.map { game => (game.path, new Game(game)) } : _*)

  def apply(gameName : String) = games.get(gameName)
}
