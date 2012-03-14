package net.vidageek.games.auth.twitter

import org.scribe.builder.api.TwitterApi
import org.scribe.builder.ServiceBuilder
import net.vidageek.games.vraptor.OAuthSecrets
import net.vidageek.games.auth.AuthProvider
import net.vidageek.games.auth.OAuthServiceBuilder
import org.scribe.model.Verifier
import org.scribe.model.Token
import org.scribe.model.OAuthRequest
import org.scribe.model.Verb
import org.scribe.model.Response
import scala.util.parsing.json.JSON

class TwitterAuthProvider(secrets: OAuthSecrets) extends AuthProvider {

  val twitterService = OAuthServiceBuilder(this, secrets)

  def applicationAuthoritionUrl: String = twitterService.autorizationUrl

  override def name: String = "twitter"

  override def accessToken(verifier: Verifier): Token = {
    twitterService.accessToken(verifier)
  }

  override def userName(accessToken: Token): String = {
    val credentials = new OAuthRequest(Verb.GET, "http://api.twitter.com/1/account/verify_credentials.json")
    twitterService.authService.signRequest(accessToken, credentials)
    val response = credentials.send
    extractUserName(response)
  }
  
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