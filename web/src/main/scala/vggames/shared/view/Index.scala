package vggames.shared.view

import vggames.shared.Game
import scalatags._
import scala.collection.mutable.Map

class Index extends TypedView[(String, Game, Map[String, String], Option[String])] {

  override def render(t : (String, Game, Map[String, String], Option[String])) = {
    val (gameName, game, finishedGroups, gameEnded) = t

    html(
      head(
        Tags.title(s"Comece a jogar o ${game.getName} Game")),
      body(
        div("alert".cls)(
          strong("Aviso!"), " Estamos em Beta. Caso encontre algum problema, envie um email para games@vidageek.net"),

        gameEnded.map { e =>
          div("alert alert-success".cls)(
            s"Parabéns! Você acabou de resolver o último exercício de ${game.getName}. O importante agora é continuar praticando.",
            br(),
            "Compartilhe a sua conquista com seus amigos no",
            a(target := "_blank", href := s"http://www.facebook.com/sharer.php?u=http://games.vidageek.net/play/${gameName}")("Facebook"),
            " e ",
            a(target := "_blank", href := s"http://twitter.com/share?text=Acabei%20de%20terminar%20o%20${game.getName}%20Game.%20Quer%20aprender%20${game.getName}%20tamb%C3%A9m%3F&url=http://games.vidageek.net/play/${gameName}")("Twitter"))
        }.getOrElse(""),

        h1(s"${game.getName} Game"),
        p(game.getDescription),
        "Você pode começar a jogar pelo ", a(href := s"/play/${gameName}/task/0")("primeiro exercício"), " ou escolher um grupo abaixo:",

        ul("nav nav-pills nav-stacked groups".cls)(
          game.groups.map { group =>
            li(finishedGroups.get(s"${gameName}.${group.taskGroup.name}").getOrElse("").cls)(
              a(href := s"/play/${gameName}/task/${group.index}")(group.taskGroup.name))
          }),
        h3("Outros recursos"),
        ul("nav nav-pills nav-stacked groups".cls)(
          li(a(href := s"/reference/${gameName}")("Referência")))))

  }

}