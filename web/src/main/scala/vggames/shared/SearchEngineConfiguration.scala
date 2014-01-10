package vggames.shared

import br.com.caelum.vraptor.{ Get, Resource, Result }
import scala.collection.JavaConverters._
import vggames.shared.vraptor.VraptorExtensions._
import vggames.shared.view.Robots

@Resource
class SearchEngineConfiguration(cfg : GamesConfiguration, result : Result) {

  @Get(Array("/robots.txt"))
  def robots {
    result.render(new Robots)(cfg.activeGames, cfg.inactiveGames)
  }

  @Get(Array("/sitemap.xml"))
  def sitemap {
    result.include("games", cfg.activeGames.asJava)
    result.include("buildDate", cfg.buildDate)
  }
}