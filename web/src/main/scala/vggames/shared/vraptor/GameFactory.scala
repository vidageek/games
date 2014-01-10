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
import java.util.concurrent.ConcurrentHashMap
import scala.util.Try

@Component
class GameFactory(cached : GameFactoryCache, data : RequestData) extends ComponentFactory[Game] {

  def getInstance : Game = cached(data.game).getOrElse(
    throw new RuntimeException("Não foi possível criar o jogo [" + data.game + "]. Talvez " +
      "seja necessário registrá-lo na GameFactory"))
}

@Component
@ApplicationScoped
class GameFactoryCache(cache : DescriptionsCache) {

  val games = new ConcurrentHashMap[String, Option[Game]]().asScala

  def apply(gameName : String) : Option[Game] = games.get(gameName) match {
    case Some(game) => game
    case None => attemptToLoad(gameName, cache)
  }

  private def attemptToLoad(name : String, cache : DescriptionsCache) : Option[Game] = {
    val engineName = s"vggames.$name.${name.capitalize}Game"
    val engine = Try(Class.forName(engineName).newInstance.asInstanceOf[GameEngine]).
      map(new Game(_))

    games.putIfAbsent(name, engine.toOption)
    games(name)
  }
}

