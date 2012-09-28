package vggames.shared.vraptor

import java.io.{ FileInputStream, File }
import java.util.Properties

import br.com.caelum.vraptor.ioc.{ Component, ApplicationScoped }

@Component
@ApplicationScoped
class Secrets {
  val oauthSecrets = readSecrets("oauth_secrets")
  val awsSecrets = readSecrets("aws_secrets")

  def apiKeyFor(providerName : String) = {
    oauthSecrets.getProperty(providerName + ".api.key", "")
  }

  def apiSecretFor(providerName : String) = {
    oauthSecrets.getProperty(providerName + ".api.secret", "")
  }

  def awsAccessKey = awsSecrets.getProperty("access.key")
  def awsSecretKey = awsSecrets.getProperty("secret.key")

  private def readSecrets(secretName : String) = {
    val secrets = new Properties()
    val file = new File(System.getProperty("user.home") + "/.vgGames/" + secretName)
    if (file.exists)
      secrets.load(new FileInputStream(file))
    secrets
  }
}