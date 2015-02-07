package vggames.shared.view

import vggames.shared.GamesConfiguration
import vggames.shared.Game
import scala.collection.concurrent.Map
import scalatags.Text.all._
import vggames.shared.vraptor.GameFactoryCache

class Home extends TypedView[(GamesConfiguration, GameFactoryCache)] {

  val title = "title".tag[String]

  override def render(t: (GamesConfiguration, GameFactoryCache)) = {
    val (cfg, cached) = t

    html(
      head(
        title(
          "VidaGeek Games")),
      body(
        div(cls := "why")(
          p("O que você quer aprender hoje?"),
          ul(cls := "game-list")(
            cfg.activeGames.map { game =>
              li(
                a(href := s"/play/$game")(img(src := s"/static/$game/logo.png", alt := s"${cached(game).map(_.name).getOrElse(game)} Game"), (s"${cached(game).map(_.name).getOrElse(game)}")))
            })
            )))

  }

}
