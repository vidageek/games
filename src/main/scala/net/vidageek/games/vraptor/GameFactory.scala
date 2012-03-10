package net.vidageek.games.vraptor

import br.com.caelum.vraptor.ioc.Component
import br.com.caelum.vraptor.ioc.ComponentFactory
import net.vidageek.games.regex.task.RegexGame
import net.vidageek.games.regex.Descriptions
import net.vidageek.games.Game

@Component
class GameFactory(descriptions: Descriptions) extends ComponentFactory[Game] {

  def getInstance: Game = new RegexGame(descriptions)
}