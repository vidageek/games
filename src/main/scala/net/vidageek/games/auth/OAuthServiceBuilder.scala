package net.vidageek.games.auth
import org.scribe.builder.api.TwitterApi
import org.scribe.builder.api.Api
import org.scribe.builder.ServiceBuilder
import org.scribe.oauth.OAuthService
import net.vidageek.games.vraptor.OAuthSecrets
import org.scribe.model.Verifier
import org.scribe.model.Token

object OAuthServiceBuilder {
  
  val apis = Map("twitter" -> classOf[TwitterApi])
  
  def apply(provider: AuthProvider, secrets: OAuthSecrets) = {
    val authService = new ServiceBuilder().provider(apis(provider.name)).apiKey(secrets.apiKeyFor(provider.name))
     .apiSecret(secrets.apiSecretFor(provider.name)).callback("http://localhost:8080/authorization").build
    new OAuthServiceBuilder(authService)
  }
  
}

class OAuthServiceBuilder(val authService: OAuthService) {
  
  val requestToken = authService.getRequestToken()
  
  val autorizationUrl = authService.getAuthorizationUrl(requestToken)
  
  def accessToken(verifier: Verifier): Token = {
    authService.getAccessToken(requestToken, verifier)
  }
  
}