package vggames.shared.auth

import org.scribe.builder.ServiceBuilder
import org.scribe.model.{Verifier, Token}
import org.scribe.oauth.OAuthService

import vggames.shared.vraptor.OAuthSecrets

object AuthenticateWithProvider {

  def apply(provider : AuthProvider, secrets : OAuthSecrets, callbackUri: String) = {
    val authService = new ServiceBuilder().provider(provider.api).apiKey(secrets.apiKeyFor(provider.name))
      .apiSecret(secrets.apiSecretFor(provider.name)).callback(callbackUri).build
    new AuthenticateWithProvider(authService)
  }
}

class AuthenticateWithProvider(authService : OAuthService) extends Serializable {
  val requestToken = authService.getRequestToken

  def authorizationUrl = authService.getAuthorizationUrl(requestToken)

  def requester(verifier: Verifier): AuthenticatedRequester = new AuthenticatedRequester(accessToken(verifier), authService)

  private def accessToken(verifier : Verifier) : Token = authService.getAccessToken(requestToken, verifier)
}