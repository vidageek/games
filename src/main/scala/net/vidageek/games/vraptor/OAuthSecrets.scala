package net.vidageek.games.vraptor

import java.io.File
import java.io.FileInputStream
import java.util.Properties

import br.com.caelum.vraptor.ioc.ApplicationScoped
import br.com.caelum.vraptor.ioc.Component

@Component
@ApplicationScoped
class OAuthSecrets {
  val properties = new Properties()
  properties.load(new FileInputStream(new File(System.getProperty("user.home") + "/.vgGames/oauth_secrets")))

  def apiKeyFor(providerName: String) = {
    properties.getProperty(providerName + ".api.key")
  }

  def apiSecretFor(providerName: String) = {
    properties.getProperty(providerName + ".api.secret")
  }
}