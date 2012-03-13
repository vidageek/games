package net.vidageek.games.auth.twitter

import org.scribe.builder.api.TwitterApi
import org.scribe.builder.ServiceBuilder
import net.vidageek.games.vraptor.OAuthSecrets
import net.vidageek.games.auth.AuthProvider
import net.vidageek.games.auth.OAuthServiceBuilder

class TwitterAuthProvider(secrets: OAuthSecrets) extends AuthProvider {
  
  val twitterService = OAuthServiceBuilder(this, secrets)

  def applicationAuthoritionUrl: String = twitterService.autorizationUrl

  def name: String = "twitter"
}