package vggames.shared.view

import scalatags._

class About extends TypedView[Unit] {

  override def render(t : Unit) = {

    html(
      head(
        Tags.title("Quem constrói esse site?")),
      body(
        div("about".cls)(
          h1("Pessoas que contribuem com o projeto"),
          div(id := "contributors")(""),
          h1("Organizações que contribuem com o projeto"),
          div(id := "organizations")(
            div(
              img(src := "http://www.adaptideas.com.br/images/logo.png", alt := "AdaptIdeas"),
              a("btn btn-info".cls, href := "http://www.adaptideas.com.br/")("AdaptIdeas")),
            div(
              img(src := s"/images/ccsl.jpeg?v=${ResourceId.ts}", alt := "CCSL"),
              a("btn btn-info".cls, href := "http://ccsl.ime.usp.br/")("CCSL")),
            div(
              img(src := "https://secure.gravatar.com/avatar/9ce5f6b25189ecb05c5c982cc5c0ad7c.png", alt := "VidaGeek.net"),
              a("btn btn-info".cls, href := "http://vidageek.net/")("VidaGeek.net"))))))

  }
}