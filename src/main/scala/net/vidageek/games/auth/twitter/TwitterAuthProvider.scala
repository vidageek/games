package net.vidageek.games.auth.twitter

import org.scribe.builder.api.TwitterApi
import org.scribe.builder.ServiceBuilder
import net.vidageek.games.vraptor.OAuthSecrets
import net.vidageek.games.auth.AuthProvider

class TwitterAuthProvider(serviceBuilder: ServiceBuilder, secrets: OAuthSecrets) extends AuthProvider {
  val twitterService = serviceBuilder.provider(new TwitterApi).apiKey(secrets.apiKeyFor("twitter"))
    .apiSecret(secrets.apiSecretFor("twitter")).callback("http://games.vidageek.net").build
  val requestToken = twitterService.getRequestToken

  def applicationAuthoritionUrl: String = twitterService.getAuthorizationUrl(requestToken)

  def name: String = "twitter"
}