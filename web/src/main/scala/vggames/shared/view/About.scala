package vggames.shared.view

import scalatags.Text.all._
import vggames.shared.GameResourceId

class About extends TypedView[Unit] {

  override def render(t: Unit) = {
    val title = "title".tag[String]

    html(
      head(
        title("Quem constrói esse site?")),
      body(
        div(cls := "about")(
          h1("Pessoas que contribuem com o projeto"),
          div(id := "contributors")(""),
          h1("Organizações que contribuem com o projeto"),
          div(id := "organizations")(
            div(
              img(src := "http://www.adaptideas.com.br/images/logo.png", alt := "AdaptIdeas"),
              a(cls := "btn btn-info", href := "http://www.adaptideas.com.br/")("AdaptIdeas")),
            div(
              img(src := s"/images/ccsl.jpeg?v=${GameResourceId.id}", alt := "CCSL"),
              a(cls := "btn btn-info", href := "http://ccsl.ime.usp.br/")("CCSL")),
            div(
              img(src := "https://secure.gravatar.com/avatar/9ce5f6b25189ecb05c5c982cc5c0ad7c.png", alt := "VidaGeek.net"),
              a(cls := "btn btn-info", href := "http://vidageek.net/")("VidaGeek.net"))))))

  }
}