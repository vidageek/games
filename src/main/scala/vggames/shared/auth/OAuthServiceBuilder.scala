package vggames.shared.auth

import org.scribe.builder.api.TwitterApi
import org.scribe.builder.ServiceBuilder
import org.scribe.model.{Verifier, Token}
import org.scribe.oauth.OAuthService

import vggames.shared.vraptor.OAuthSecrets

object OAuthServiceBuilder {

  def apply(provider : AuthProvider, secrets : OAuthSecrets, callbackUri: String) = {
    val authService = new ServiceBuilder().provider(provider.api).apiKey(secrets.apiKeyFor(provider.name))
      .apiSecret(secrets.apiSecretFor(provider.name)).callback(callbackUri).build
    new OAuthServiceBuilder(authService)
  }
}

class OAuthServiceBuilder(val authService : OAuthService) {
  val requestToken = authService.getRequestToken
  val autorizationUrl = authService.getAuthorizationUrl(requestToken)

  def accessToken(verifier : Verifier) : Token = {
    authService.getAccessToken(requestToken, verifier)
  }
}