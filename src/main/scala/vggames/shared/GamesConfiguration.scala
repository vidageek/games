package vggames.shared

import br.com.caelum.vraptor.ioc.ApplicationScoped
import br.com.caelum.vraptor.ioc.Component
import java.text.SimpleDateFormat
import java.util.Date

import scala.collection.JavaConverters._

@ApplicationScoped
@Component
class GamesConfiguration {

  val buildDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date)

  def activeGames = List("regex", "git", "meta")

  def inactiveGames = List("scala", "css", "html", "math")

  def getActiveGames = activeGames.asJava

  def getInactiveGames = inactiveGames.asJava

}
