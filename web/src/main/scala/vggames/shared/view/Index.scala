package vggames.shared.view

import vggames.shared.Game
import scalatags.Text.all._
import scala.collection.mutable.Map
import scalatags.Text.TypedTag

class Index extends TypedView[(String, Game, Map[String, String], Option[String])] {

  val title = "title".tag[String]

  override def render(t: (String, Game, Map[String, String], Option[String])) = {
    val (gameName, game, finishedGroups, gameEnded) = t

    html(
      head(
        title(s"Jogue ${game.name} Game")),
      body(
        div(cls := "alert")(
          strong("Aviso!"), " Estamos em Beta. Caso encontre algum problema, envie um email para games@vidageek.net"),
        gameEnded.map { e =>
          div(cls := "alert alert-success")(
            s"Parabéns! Você acabou de resolver o último exercício de ${game.name}. O importante agora é continuar praticando.",
            br(),
            "Compartilhe a sua conquista com seus amigos no",
            a(target := "_blank", href := s"http://www.facebook.com/sharer.php?u=http://games.vidageek.net/play/${gameName}")("Facebook"),
            " e ",
            a(target := "_blank", href := s"http://twitter.com/share?text=Acabei%20de%20terminar%20o%20${game.name}%20Game.%20Quer%20aprender%20${game.name}%20tamb%C3%A9m%3F&url=http://games.vidageek.net/play/${gameName}")("Twitter"))
        }.getOrElse(""),

        h1(s"${game.name} Game"),

        p(raw(game.description)),
        "Você pode começar a jogar pelo ", a(href := s"/play/${gameName}/task/0")("primeiro exercício"), " ou escolher um grupo abaixo:",

        if (game.hasTutor)
          p("cls".attr := "tutor")("Este jogo não possui um corretor automático, mas você pode ",
          "contratar um tutor para corrigir os seus exercícios. Envie um email para jonas@vidageek.net ",
          "para saber mais")
        else "",

        a(id := "conteudo", cls := "theory-link")(""),
        h2(cls := "theory")("Conteúdo:"),
        ul(cls := "nav nav-pills nav-stacked groups")(
          game.groups.map { group =>
            li(cls := finishedGroups.get(s"${gameName}.${group.taskGroup.id}").getOrElse(""))(
              a(href := s"/play/${gameName}/task/${group.index}")(group.taskGroup.htmlName))
          }),
        game.groups.map { group =>
          (a(id := group.taskGroup.id, cls := "theory-link")(""),
            div(
              h2(
                a(href := s"#${group.taskGroup.id}")(group.taskGroup.name)),
              raw(group.description),
              a(cls := "btn", href := "#conteudo")("Topo"),
              a(cls := "btn btn-info", href := s"/play/${gameName}/task/${group.index}")("Jogar!")))
        }.foldLeft(List[TypedTag[String]]())((acc, t) => acc :+ t._1 :+ t._2)))

  }

}
