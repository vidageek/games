package vggames.shared

import br.com.caelum.vraptor.{ Get, Resource, Result }
import vggames.shared.vraptor.VraptorExtensions._
import vggames.shared.view.Robots
import vggames.shared.view.Sitemap

@Resource
class SearchEngineConfiguration(cfg : GamesConfiguration, result : Result) {

  @Get(Array("/robots.txt"))
  def robots {
    result.render(new Robots)(cfg.activeGames, cfg.inactiveGames)
  }

  @Get(Array("/sitemap.xml"))
  def sitemap {
    result.render(new Sitemap)(cfg.activeGames, cfg.buildDate)
  }
}