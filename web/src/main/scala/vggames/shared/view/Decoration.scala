package vggames.shared.view

import br.com.caelum.vraptor.ioc.Component
import javax.servlet.http.HttpServletRequest
import scalatags.Text.all._
import vggames.shared.GameResourceId
import vggames.shared.player.PlayerSession
import vggames.shared.vraptor.Params

@Component
class Decoration(req: HttpServletRequest, params: Params, session: PlayerSession) {

  val section = "section".tag[String]

  def apply(html: String) = {
    val head = find(html, "head")
    val body = find(html, "body")

    val reqUrl = req.getRequestURI

    if (reqUrl.contains("/play") && reqUrl.contains("/resource"))
      ""
    else
      wrap(head, body)
  }

  def wrap(headContent: String, bodyContent: String) = {

    "<!DOCTYPE html>" +
      html(lang := "pt-br")(
        head(
          meta("http-equiv".attr := "Content-Type", "content".attr := "text/html; charset=UTF-8"),
          meta(name := "google-site-verification", "content".attr := "FjDxiddkSQXOHTFWCpYqrXpjj7wCoCcX1krRxTCHuq0"),
          meta(name := "google-site-verification", "content".attr := "igmWNUz0B_VblujqXG47m32FBgjyQ5Zc2Oq-3YzSZv8"),
          raw(headContent),
          link(href := s"/css/games.css?v=${GameResourceId.id}", rel := "stylesheet", "type".attr := "text/css", "media".attr := "screen"),
          params.game.map { game =>
            link(href := s"/css/${params.gameId}.css?v=${GameResourceId.id}", rel := "stylesheet", "type".attr := "text/css", "media".attr := "screen")
          }.getOrElse(raw("")),
          script("type".attr := "text/javascript")(analyticsCode)),
        body(
          div(cls := "navbar navbar-fixed-top")(
            div(cls := "navbar-inner")(
              div(cls := "container")(
                params.game.map { game =>
                  a(cls := "brand", href := s"/play/${params.gameId}")(
                    s"${game.name} Game")
                }.getOrElse(raw("")),
                session.actualPlayer.map { player =>
                  div(cls := "nav-collapse")(
                    ul(cls := "nav")(
                      li(id := "level")(
                        a(href := "#", style := s"""
                            		background: -moz-linear-gradient(left center, #8CF ${player.progress}%, #EEE ${player.progress}%);
    								background: -webkit-gradient(linear, left top, right top, color-stop(${player.progress}%,#8CF), color-stop(${player.progress}%,#EEE));
    								background: linear-gradient(left center, #8CF ${player.progress}%, #EEE ${player.progress}%);
                            		""")(
                          span(player.level.toString))),
                      li(cls := "active")(
                        a(id := "logged", cls := "pull-right nav-link", href := "/progress")(player.email)),
                      li(
                        player.lastTask.map { lastTask =>
                          a(cls := "pull-right nav-link", href := lastTask)(
                            "Última Jogada")
                        }.getOrElse(raw(""))),
                      li(
                        a(href := "/about")(
                          "Sobre")),
                      li(cls := "divider-vertical"),
                      li(
                        a(cls := "pull-right nav-link", href := "/logout")(
                          "Logout"))))
                }.getOrElse {
                  ul(cls := "nav")(
                    li(cls := "active")(
                      a(id := "select-a-provider", cls := "brand pull-right nav-link", "data-toggle".attr := "modal", href := "#logon-provider")(
                        "Login")),
                    li(a(href := "/about")("Sobre")))
                },
                div(cls := "ribbon-holder")(
                  a(href := "https://github.com/vidageek/games", cls := "ribbon")(
                    span(cls := "text")("Ajude-nos no GitHub")))))),

          div(id := "main-content", cls := "container")(
            params.notice.map { notice =>
              div(cls := "alert alert-info")(notice)
            }.getOrElse(raw("")),
            raw(bodyContent)),

          section(
            div(id := "logon-provider", cls := "modal hide fade in")(
              div(cls := "modal-body")(
                a(cls := "close", "data-dismiss".attr := "modal")("X"),
                form(action := "/player", "method".attr := "POST")(
                  label("for".attr := "email")("Email:"),
                  input(name := "email"),
                  input("type".attr := "submit", value := "Logar")),
                div(cls := "alert alert-info")(
                  a(href := "/senha")("Porquê não preciso colocar minha senha?"))))),
          script("type".attr := "text/javascript", src := "http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js")(""),
          script("type".attr := "text/javascript", src := s"/js/games.js?v=${GameResourceId.id}")(""),
          params.game.map { game =>
            script("type".attr := "text/javascript", src := s"/js/${params.gameId}.js?v=${GameResourceId.id}")("")
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

  def find(html: String, element: String) =
    html.substring(html.indexOf(s"<$element>") + s"<$element>".length(), html.indexOf(s"</$element>"))

}