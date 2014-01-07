package vggames.shared.vraptor

import java.io.{ FileInputStream, File }
import java.util.Properties

import br.com.caelum.vraptor.ioc.{ Component, ApplicationScoped }

@Component
@ApplicationScoped
class Secrets {
  val awsSecrets = readSecrets("aws_secrets")

  def awsAccessKey = key(awsSecrets.getProperty("access.key"))
  def awsSecretKey = key(awsSecrets.getProperty("secret.key"))

  private def key(f : => String) : Option[String] = Option(f)

  private def readSecrets(secretName : String) = {
    val secrets = new Properties()
    val file = new File(System.getProperty("user.home") + "/.vgGames/" + secretName)
    if (file.exists)
      secrets.load(new FileInputStream(file))
    secrets
  }
}