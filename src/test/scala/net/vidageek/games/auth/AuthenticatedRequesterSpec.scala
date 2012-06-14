package net.vidageek.games.auth

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito
import org.scribe.oauth.OAuthService
import org.scribe.model.{OAuthRequest, Response, Token}

class AuthenticatedRequesterSpec extends Specification with Mockito {

  "Authentication Requester" should {
    "sends a request" in {
      val accessToken = mock[Token]
      val oauthService = mock[OAuthService]
      val response:Response = AuthenticatedRequester(accessToken, oauthService).get("http://www.google.com.br")
      (response.getCode / 100) should_== 2
    }

    "signs request" in {
      val accessToken = mock[Token]
      val oauthService = mock[OAuthService]
      AuthenticatedRequester(accessToken, oauthService).get("http://www.google.com.br")
      there was one(oauthService).signRequest(===(accessToken), any[OAuthRequest])
    }
  }

}
