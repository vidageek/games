package vggames.shared.auth.twitter

import scala.util.parsing.json.JSON
import org.scribe.model.{ Verifier, Token, Response, OAuthRequest }
import br.com.caelum.vraptor.ioc.{ Component, ApplicationScoped }
import vggames.shared.auth.AuthenticatedRequester
import vggames.shared.auth.OAuthServiceBuilder
import vggames.shared.vraptor.OAuthSecrets
import vggames.shared.auth.AuthProvider

class TwitterAuthProvider(secrets : OAuthSecrets) extends AuthProvider {

  val twitterService = OAuthServiceBuilder(this, secrets)
  var accessToken : Token = _
  var requester : AuthenticatedRequester = null

  def applicationAuthorizationUrl = twitterService.autorizationUrl

  override def name = "twitter"

  override def accessToken(verifier : Verifier) = {
    accessToken = twitterService.accessToken(verifier)
    requester = new AuthenticatedRequester(accessToken, twitterService.authService)
    accessToken
  }

  override def logout {
    requester.post("https://api.twitter.com/1/account/end_session.json")
  }

  override def userName : String = {
    extractUserName(requester.get("http://api.twitter.com/1/account/verify_credentials.json"))
  }
  private def sign(request : OAuthRequest) = twitterService.authService.signRequest(accessToken, request)

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