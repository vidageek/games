package vggames.shared

import br.com.caelum.vraptor.{ Get, Resource, Result }
import scala.collection.JavaConverters._

@Resource
class SearchEngineConfiguration(cfg : GamesConfiguration, result : Result) {

  @Get(Array("/robots.txt"))
  def robots {
    result.include("games", cfg.activeGames.asJava)
    result.include("inactiveGames", cfg.inactiveGames.asJava)
  }

}