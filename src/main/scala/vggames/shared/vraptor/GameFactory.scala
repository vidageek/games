package vggames.shared.vraptor

import br.com.caelum.vraptor.ioc.Component
import br.com.caelum.vraptor.ioc.ComponentFactory
import vggames.regex.RegexGame
import vggames.shared.Game
import vggames.shared.task.Descriptions
import vggames.scala.ScalaGame
import vggames.css.CssGame
import br.com.caelum.vraptor.ioc.Component
import br.com.caelum.vraptor.ioc.ApplicationScoped
import vggames.html.HtmlGame
import vggames.git.GitGame

@Component
class GameFactory(cached : GameFactoryCache, data : RequestData) extends ComponentFactory[Game] {

  def getInstance : Game = {
    data.game match {
      case "regex" => cached regexGame
      case "git" => cached gitGame
      case "scala" => cached scalaGame
      case "css" => cached cssGame
      case "html" => cached htmlGame
      case other => throw new RuntimeException("Não foi possível criar o jogo [" + other + "]. Talvez " +
        "seja necessário registrá-lo na GameFactory")
    }
  }
}

@Component
@ApplicationScoped
class GameFactoryCache(cache : DescriptionsCache) {
  val regexGame = new RegexGame(cache.get("regex"))
  val scalaGame = new ScalaGame(cache.get("scala"))
  val cssGame = new CssGame(cache.get("css"))
  val htmlGame = new HtmlGame(cache.get("css"))
  val gitGame = new GitGame(cache.get("git"))
}