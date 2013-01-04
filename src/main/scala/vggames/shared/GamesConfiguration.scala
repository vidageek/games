package vggames.shared

import br.com.caelum.vraptor.ioc.ApplicationScoped
import br.com.caelum.vraptor.ioc.Component
import java.text.SimpleDateFormat
import java.util.Date

@ApplicationScoped
@Component
class GamesConfiguration {

  val buildDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date)

  def activeGames = List("regex")

  def inactiveGames = List("scala", "css", "html", "sql", "git")

}