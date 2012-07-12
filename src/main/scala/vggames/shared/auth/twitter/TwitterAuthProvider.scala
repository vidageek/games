package vggames.shared.auth.twitter

import scala.util.parsing.json.JSON

import org.scribe.model.{Verifier, Token, Response, OAuthRequest}

import vggames.shared.auth.{OAuthServiceBuilder, AuthenticatedRequester, AuthProvider}
import vggames.shared.vraptor.OAuthSecrets

class TwitterAuthProvider(secrets : OAuthSecrets) extends AuthProvider {

  val twitterService = OAuthServiceBuilder(this, secrets)
  var accessToken : Token = _
  var requester : Option[AuthenticatedRequester] = None

  def applicationAuthorizationUrl = twitterService.autorizationUrl

  override def name = "twitter"

  override def accessToken(verifier : Verifier) = {
    requester = Some(new AuthenticatedRequester(twitterService.accessToken(verifier), twitterService.authService))
  }

  override def logout {
    requester.get.post("https://api.twitter.com/1/account/end_session.json")
  }

  override def userName : String = {
    extractUserName(requester.get.get("http://api.twitter.com/1/account/verify_credentials.json"))
  }

  private def extractUserName(response : Response) : String = {
    JSON.parseFull(response.getBody) match {
      case Some(x) => {
        val m = x.asInstanceOf[Map[String, String]]
        m("screen_name")
      }
      case _ => ""
    }
  }
}