package net.vidageek.games.auth

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito
import org.scribe.oauth.OAuthService
import org.scribe.model.{OAuthRequest, Response, Token}
import org.specs2.specification.Scope

class AuthenticatedRequesterSpec extends Specification {
  "Authentication Requester" should {
    "sends a get" in new RequesterInformations {
      val response:Response = requester.get("http://www.google.com.br")
      (response.getCode / 100) should_== 2
    }

    "sends a post" in new RequesterInformations {
      val response:Response = requester.post("http://www.google.com.br")
      (response.getCode / 100) should_== 2
    }

    "signs request" in new RequesterInformations {
      requester.get("http://www.google.com.br")
      there was one(oauthService).signRequest(===(accessToken), any[OAuthRequest])
    }
  }
}

trait RequesterInformations extends Scope with Mockito {
  var accessToken:Token = mock[Token]
  var oauthService:OAuthService = mock[OAuthService]
  var requester:AuthenticatedRequester = AuthenticatedRequester(accessToken, oauthService)
}
