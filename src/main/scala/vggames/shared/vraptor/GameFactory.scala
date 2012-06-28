package vggames.shared.vraptor

import br.com.caelum.vraptor.ioc.Component
import br.com.caelum.vraptor.ioc.ComponentFactory
import vggames.regex.task.RegexGame
import vggames.shared.Game
import vggames.shared.task.Descriptions
import vggames.scala.ScalaGame

@Component
class GameFactory(descriptions : Descriptions, data : RequestData, regexGame : RegexGame, scalaGame : ScalaGame) extends ComponentFactory[Game] {

  def getInstance : Game = {
    data.game match {
      case "regex" => regexGame
      case "scala" => scalaGame
      case other => throw new RuntimeException("Não foi possível criar o jogo [" + other + "]. Talvez " +
        "seja necessário registrá-lo na GameFactory")
    }
  }

}