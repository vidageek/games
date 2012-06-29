package vggames.shared.auth

import org.scribe.model.{Verifier, Token}

import vggames.shared.vraptor.OAuthSecrets

class GoogleAuthProvider(secrets : OAuthSecrets) extends AuthProvider {
  val googleService = OAuthServiceBuilder(this, secrets)
  var accessToken : Token = _

  override def applicationAuthorizationUrl = googleService.autorizationUrl

  override def name = "google"

  override def accessToken(verifier : Verifier) = {
    accessToken = googleService.accessToken(verifier)
    accessToken
  }

  override def userName : String = ""

  override def logout : Unit = {}
}