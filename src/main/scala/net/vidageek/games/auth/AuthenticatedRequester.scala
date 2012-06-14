package net.vidageek.games.auth

import org.scribe.model.{Verb, OAuthRequest, Response, Token}
import org.scribe.oauth.OAuthService


object AuthenticatedRequester {
  def apply(accessToken: Token, oauthService: OAuthService) = {
    new AuthenticatedRequester(accessToken, oauthService)
  }
}

class AuthenticatedRequester(accessToken: Token, oauthService: OAuthService) {
  def get(url:String): Response = {
    request(Verb.GET, url)
  }

  private def request(verb: Verb, url: String) = {
    val get = new OAuthRequest(Verb.GET, url)
    oauthService.signRequest(accessToken, get)
    get.send
  }
}
