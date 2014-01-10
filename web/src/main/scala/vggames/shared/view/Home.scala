package vggames.shared.view

import vggames.shared.GamesConfiguration
import vggames.shared.Game
import scala.collection.concurrent.Map
import scalatags._
import vggames.shared.vraptor.GameFactoryCache

class Home extends TypedView[(GamesConfiguration, GameFactoryCache)] {

  override def render(t : (GamesConfiguration, GameFactoryCache)) = {
    val (cfg, cached) = t

    html(
      head(
        Tags.title(
          "VidaGeek Games")),
      body(
        header(
          h1(
            "VidaGeek Games")),
        div("row why span8 offset2".cls)(
          p("O VidaGeek Games é uma iniciativa ", strong("OpenSource"), " para criar material de aprendizado atravéz da prática.",
            "Para isso criamos ", strong("jogos"), " nos quais você ", strong("aprende"), " conforme avança."),
          p("Ainda existe muito trabalho a ser feito, mas você já pode sentir o gostinho com alguns dos jogos abaixo:"),
          ul(
            cfg.activeGames.map { game =>
              li(
                a(href := s"/play/$game")(s"${cached(game).map(_.getName).getOrElse(game)} :"),
                p(s"${cached(game).map(_.getDescription).getOrElse("")}"))
            }),
          p("E também estamos trabalhando em jogos sobre:"),
          ul(
            cfg.inactiveGames.map { game =>
              li(s"${cached(game).map(_.getName).getOrElse(game)}")
            }))))

  }

}