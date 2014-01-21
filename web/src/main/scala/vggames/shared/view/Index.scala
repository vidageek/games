package vggames.shared.view

import vggames.shared.Game
import scalatags._
import scala.collection.mutable.Map

class Index extends TypedView[(String, Game, Map[String, String], Option[String])] {

  override def render(t : (String, Game, Map[String, String], Option[String])) = {
    val (gameName, game, finishedGroups, gameEnded) = t

    html(
      head(
        Tags.title(s"Referência do ${game.name} Game")),
      body(
        div("alert".cls)(
          strong("Aviso!"), " Estamos em Beta. Caso encontre algum problema, envie um email para games@vidageek.net"),
        gameEnded.map { e =>
          div("alert alert-success".cls)(
            s"Parabéns! Você acabou de resolver o último exercício de ${game.name}. O importante agora é continuar praticando.",
            br(),
            "Compartilhe a sua conquista com seus amigos no",
            a(target := "_blank", href := s"http://www.facebook.com/sharer.php?u=http://games.vidageek.net/play/${gameName}")("Facebook"),
            " e ",
            a(target := "_blank", href := s"http://twitter.com/share?text=Acabei%20de%20terminar%20o%20${game.name}%20Game.%20Quer%20aprender%20${game.name}%20tamb%C3%A9m%3F&url=http://games.vidageek.net/play/${gameName}")("Twitter"))
        }.getOrElse(""),

        h1(s"${game.name} Game"),

        p(game.description),
        "Você pode começar a jogar pelo ", a(href := s"/play/${gameName}/task/0")("primeiro exercício"), " ou escolher um grupo abaixo:",

        a(id := "conteudo", "theory-link".cls)(""),
        h2("theory".cls)("Conteúdo:"),
        ul("nav nav-pills nav-stacked groups".cls)(
          game.groups.map { group =>
            li(finishedGroups.get(s"${gameName}.${group.taskGroup.groupName}").getOrElse("").cls)(
              a(href := s"/play/${gameName}/task/${group.index}")(group.taskGroup.htmlName))
          }),
        game.groups.map { group =>
          (a(id := group.taskGroup.groupName, "theory-link".cls)(""),
            div(
              h1(
                a(href := s"#${group.taskGroup.groupName}")(group.taskGroup.name)),
              raw(group.description),
              a("btn".cls, href := "#conteudo")("Topo"),
              a("btn btn-info".cls, href := s"/play/${gameName}/task/${group.index}")("Jogar!")))
        }.foldLeft(List[HtmlTag]())((acc, t) => acc :+ t._1 :+ t._2)))

  }

}