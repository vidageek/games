package vggames.shared.view

import vggames.shared.GamesConfiguration
import vggames.shared.Game
import scalatags._
import vggames.shared.vraptor.GameFactoryCache
import scala.collection.mutable.Map

class Reference extends TypedView[(String, Game, Map[String, String])] {

  override def render(t : (String, Game, Map[String, String])) = {
    val (gameName, game, finishedGroups) = t

    html(
      head(
        Tags.title(s"Referência do ${game.name} Game")),
      body(
        h1(s"Referência do ${game.name} Game"),
        a(id := "conteudo", "theory-link".cls)(""),
        h2("theory".cls)("Conteúdo:"),
        ul("nav nav-pills nav-stacked groups".cls)(
          game.groups.map { group =>
            li(finishedGroups.get(s"${gameName}.${group.taskGroup.groupName}").getOrElse("").cls)(
              a(href := s"#${group.taskGroup.groupName}")(group.taskGroup.htmlName))
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