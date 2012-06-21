package vggames.shared.vraptor

import br.com.caelum.vraptor.ioc.Component
import br.com.caelum.vraptor.ioc.ComponentFactory
import vggames.regex.task.RegexGame
import vggames.shared.Game
import vggames.shared.task.Descriptions

@Component
class GameFactory(descriptions : Descriptions) extends ComponentFactory[Game] {

  def getInstance : Game = new RegexGame(descriptions)
}