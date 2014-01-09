package vggames.shared.vraptor

import br.com.caelum.vraptor.ioc.Component
import br.com.caelum.vraptor.ioc.ComponentFactory
import vggames.regex.RegexGame
import vggames.scala.ScalaGame
import vggames.css.CssGame
import br.com.caelum.vraptor.ioc.Component
import br.com.caelum.vraptor.ioc.ApplicationScoped
import vggames.html.HtmlGame
import vggames.git.GitGame
import vggames.meta.MetaGame
import vggames.math.MathGame

@Component
class GameFactory(cached : GameFactoryCache, data : RequestData) extends ComponentFactory[Game] {

  def getInstance : Game = cached(data.game).getOrElse(
    throw new RuntimeException("Não foi possível criar o jogo [" + data.game + "]. Talvez " +
      "seja necessário registrá-lo na GameFactory"))
}

@Component
@ApplicationScoped
class GameFactoryCache(cache : DescriptionsCache) {
  val games = Map(
    "regex" -> new RegexGame(cache.get("regex")),
    "scala" -> new ScalaGame(cache.get("scala")),
    "css" -> new CssGame(cache.get("css")),
    "html" -> new HtmlGame(cache.get("html")),
    "git" -> new GitGame(cache.get("git")),
    "meta" -> new MetaGame(cache.get("meta")),
    "math" -> new MathGame(cache.get("math")))

  def apply(gameName : String) = games.get(gameName)
}
