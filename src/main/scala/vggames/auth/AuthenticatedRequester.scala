package vggames.auth

import org.scribe.model.OAuthRequest
import org.scribe.model.Response
import org.scribe.model.Token
import org.scribe.model.Verb
import org.scribe.oauth.OAuthService

object AuthenticatedRequester {
  def apply(accessToken : Token, oauthService : OAuthService) = {
    new AuthenticatedRequester(accessToken, oauthService)
  }
}

class AuthenticatedRequester(accessToken : Token, oauthService : OAuthService) {
  def get(url : String) : Response = {
    request(Verb.GET, url)
  }

  def post(url : String) : Response = {
    request(Verb.POST, url)
  }

  private def request(verb : Verb, url : String) = {
    val get = new OAuthRequest(Verb.GET, url)
    oauthService.signRequest(accessToken, get)
    get.send
  }
}
