package vggames.shared.auth.twitter

import scala.util.parsing.json.JSON

import org.scribe.model.{Verifier, Token, Response, OAuthRequest}

import vggames.shared.auth.{AuthenticateWithProvider, AuthenticatedRequester, AuthProvider}
import vggames.shared.vraptor.OAuthSecrets
import org.scribe.builder.ServiceBuilder
import org.scribe.builder.api.{TwitterApi, Api}

class TwitterAuthProvider(requester: AuthenticatedRequester) extends AuthProvider {
  override def name = "twitter"

  override def logout {
    requester.post("https://api.twitter.com/1/account/end_session.json")
  }

  override def userName : String = {
    extractUserName(requester.get("http://api.twitter.com/1/account/verify_credentials.json"))
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