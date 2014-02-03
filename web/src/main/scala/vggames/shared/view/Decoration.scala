package vggames.shared.view

import br.com.caelum.vraptor.ioc.Component
import javax.servlet.http.HttpServletRequest
import scala.util.matching.Regex
import vggames.shared.player.PlayerSession
import vggames.shared.vraptor.Params
import scalatags._
import java.util.Date

object ResourceId {
  val ts = new Date().getTime
}

@Component
class Decoration(req : HttpServletRequest, params : Params, session : PlayerSession) {

  def apply(html : String) = {
    val head = find(html, "head")
    val body = find(html, "body")

    val reqUrl = req.getRequestURI

    if (reqUrl.contains("/play") && reqUrl.contains("/resource"))
      ""
    else
      wrap(head, body)
  }

  def wrap(headContent : String, bodyContent : String) = {

    "<!DOCTYPE html>" +
      html(lang := "pt-br")(
        head(
          meta("http-equiv".attr := "Content-Type", "content".attr := "text/html; charset=UTF-8"),
          meta(name := "google-site-verification", "content".attr := "FjDxiddkSQXOHTFWCpYqrXpjj7wCoCcX1krRxTCHuq0"),
          meta(name := "google-site-verification", "content".attr := "igmWNUz0B_VblujqXG47m32FBgjyQ5Zc2Oq-3YzSZv8"),
          raw(headContent),
          link(href := s"/css/games.css?v=${ResourceId.ts}", rel := "stylesheet", "type".attr := "text/css", "media".attr := "screen"),
          params.game.map { game =>
            link(href := s"/css/${params.gameId}.css?v=${ResourceId.ts}", rel := "stylesheet", "type".attr := "text/css", "media".attr := "screen")
          }.getOrElse(raw("")),
          script("type".attr := "text/javascript")(analyticsCode)),
        body(
          div("navbar navbar-fixed-top".cls)(
            div("navbar-inner".cls)(
              div("container".cls)(
                params.game.map { game =>
                  a("brand".cls, href := s"/play/${params.gameId}")(
                    s"${game.name} Game")
                }.getOrElse(raw("")),
                session.actualPlayer.map { player =>
                  div("nav-collapse".cls)(
                    ul("nav".cls)(
                      li(id := "level")(
                        a(href := "#", style := s"""
                            		background: -moz-linear-gradient(left center, #8CF ${player.progress}%, #EEE ${player.progress}%);
    								background: -webkit-gradient(linear, left top, right top, color-stop(${player.progress}%,#8CF), color-stop(${player.progress}%,#EEE));
    								background: linear-gradient(left center, #8CF ${player.progress}%, #EEE ${player.progress}%);
                            		""")(
                          span(player.level.toString))),
                      li("active".cls)(
                        a(id := "logged", "pull-right nav-link".cls, href := "/progress")(player.email)),
                      li(
                        player.lastTask.map { lastTask =>
                          a("pull-right nav-link".cls, href := lastTask)(
                            "Última Jogada")
                        }.getOrElse(raw(""))),
                      li(
                        a(href := "/about")(
                          "Sobre")),
                      li("divider-vertical".cls),
                      li(
                        a("pull-right nav-link".cls, href := "/logout")(
                          "Logout"))))
                }.getOrElse {
                  ul("nav".cls)(
                    li("active".cls)(
                      a(id := "select-a-provider", "brand pull-right nav-link".cls, "data-toggle".attr := "modal", href := "#logon-provider")(
                        "Login")),
                    li(a(href := "/about")("Sobre")))
                },
                div("ribbon-holder".cls)(
                  a(href := "https://github.com/vidageek/games", "ribbon".cls)(
                    span("text".cls)("Ajude-nos no GitHub")))))),

          div(id := "main-content", "container".cls)(
            params.notice.map { notice =>
              div("alert alert-info".cls)(notice)
            }.getOrElse(raw("")),
            raw(bodyContent)),

          "section".tag(
            div(id := "logon-provider", "modal hide fade in".cls)(
              div("modal-body".cls)(
                a("close".cls, "data-dismiss".attr := "modal")("X"),
                form(action := "/player", "method".attr := "POST")(
                  label("for".attr := "email")("Email:"),
                  input(name := "email"),
                  input("type".attr := "submit", value := "Logar")),
                div("alert alert-info".cls)(
                  a(href := "/senha")("Porquê não preciso colocar minha senha?"))))),
          script("type".attr := "text/javascript", src := "http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js")(""),
          script("type".attr := "text/javascript", src := s"/js/games.js?v=${ResourceId.ts}")(""),
          params.game.map { game =>
            script("type".attr := "text/javascript", src := s"/js/${params.gameId}.js?v=${ResourceId.ts}")("")
          }.getOrElse(raw(""))))
  }

  def analyticsCode = """
		var _gaq = _gaq || [];
		_gaq.push([ '_setAccount', 'UA-1508082-6' ]);
		_gaq.push([ '_trackPageview' ]);

		(function() {
			var ga = document.createElement('script');
			ga.type = 'text/javascript';
			ga.async = true;
			ga.src = ('https:' == document.location.protocol ? 'https://ssl'
					: 'http://www')
					+ '.google-analytics.com/ga.js';
			var s = document.getElementsByTagName('script')[0];
			s.parentNode.insertBefore(ga, s);
		})();"""

  def find(html : String, element : String) =
    html.substring(html.indexOf(s"<$element>") + s"<$element>".length(), html.indexOf(s"</$element>"))

}