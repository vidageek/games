package net.vidageek.games.auth.twitter

import scala.util.parsing.json.JSON

import org.scribe.model.OAuthRequest
import org.scribe.model.Response
import org.scribe.model.Token
import org.scribe.model.Verifier

import net.vidageek.games.vraptor.OAuthSecrets
import net.vidageek.games.auth.{AuthenticatedRequester, AuthProvider, OAuthServiceBuilder}

class TwitterAuthProvider(secrets: OAuthSecrets) extends AuthProvider {

  val twitterService = OAuthServiceBuilder(this, secrets)
  var accessToken: Token = _
  var requester:AuthenticatedRequester = null

  def applicationAuthorizationUrl = twitterService.autorizationUrl

  override def name = "twitter"

  override def accessToken(verifier: Verifier) = {
    accessToken = twitterService.accessToken(verifier)
    requester = new AuthenticatedRequester(accessToken, twitterService.authService)
    accessToken
  }

  override def logout {
    requester.post("https://api.twitter.com/1/account/end_session.json")
  }

  override def userName: String = {
    extractUserName(requester.get("http://api.twitter.com/1/account/verify_credentials.json"))
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