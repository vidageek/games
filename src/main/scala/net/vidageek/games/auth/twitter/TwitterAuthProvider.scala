package net.vidageek.games.auth.twitter

import scala.util.parsing.json.JSON

import org.scribe.model.OAuthRequest
import org.scribe.model.Response
import org.scribe.model.Token
import org.scribe.model.Verb
import org.scribe.model.Verifier

import br.com.caelum.vraptor.ioc.ApplicationScoped
import br.com.caelum.vraptor.ioc.Component
import net.vidageek.games.auth.AuthProvider
import net.vidageek.games.auth.OAuthServiceBuilder
import net.vidageek.games.vraptor.OAuthSecrets

class TwitterAuthProvider(secrets: OAuthSecrets) extends AuthProvider {

  val twitterService = OAuthServiceBuilder(this, secrets)
  var accessToken: Token = _

  def applicationAuthorizationUrl = twitterService.autorizationUrl

  override def name = "twitter"

  override def accessToken(verifier: Verifier) = {
    accessToken = twitterService.accessToken(verifier)
    accessToken
  }

  override def logout {
    val request = new OAuthRequest(Verb.POST, "https://api.twitter.com/1/account/end_session.json")
    sign(request)
    request.send
  }

  override def userName: String = {
    val credentials = new OAuthRequest(Verb.GET, "http://api.twitter.com/1/account/verify_credentials.json")
    sign(credentials)
    extractUserName(credentials.send)
  }
  private def sign(request: OAuthRequest) = twitterService.authService.signRequest(accessToken, request)

  private def extractUserName(response: Response): String = {
    JSON.parseFull(response.getBody) match {
      case Some(x) => {
        val m = x.asInstanceOf[Map[String, String]]
        m("screen_name")
      }
      case _ => ""
    }
  }
}