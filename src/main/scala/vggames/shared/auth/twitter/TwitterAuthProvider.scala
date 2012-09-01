package vggames.shared.auth.twitter

import scala.util.parsing.json.JSON

import org.scribe.model.{Verifier, Token, Response, OAuthRequest}

import vggames.shared.auth.{OAuthServiceBuilder, AuthenticatedRequester, AuthProvider}
import vggames.shared.vraptor.OAuthSecrets
import org.scribe.builder.ServiceBuilder
import org.scribe.builder.api.{TwitterApi, Api}

class TwitterAuthProvider(secrets : OAuthSecrets, callbackUrl:String) extends AuthProvider {
  val twitterService = OAuthServiceBuilder(this, secrets, callbackUrl + "/" + name)
  var accessToken : Token = _
  var requester : Option[AuthenticatedRequester] = None

  def applicationAuthorizationUrl = twitterService.autorizationUrl

  override def name = "twitter"

  override def accessToken(verifier : Verifier) = {
    requester = Some(new AuthenticatedRequester(twitterService.accessToken(verifier), twitterService.authService))
  }

  override def logout {
    authenticatedRequester.post("https://api.twitter.com/1/account/end_session.json")
  }

  private def authenticatedRequester = {
    requester.get
  }

  override def userName : String = {
    extractUserName(requester.get.get("http://api.twitter.com/1/account/verify_credentials.json"))
  }

  override def api: Class[_ <: Api] = classOf[TwitterApi]

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