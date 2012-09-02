package vggames.shared.auth

import org.scribe.model.{Verifier, Token}

import vggames.shared.vraptor.OAuthSecrets
import org.scribe.builder.api.{Api, GoogleApi}

class GoogleAuthProvider(secrets : OAuthSecrets, callbackUrl: String) extends AuthProvider {
  val googleService = AuthenticateWithProvider(this, secrets, callbackUrl + "/" + name)
  var accessToken : Token = _

  override def applicationAuthorizationUrl = googleService.authorizationUrl

  override def name = "google"

  override def accessToken(verifier : Verifier) = {
    accessToken = googleService.accessToken(verifier)
    accessToken
  }

  override def userName : String = ""

  override def logout : Unit = {}

  override def api: Class[_ <: Api] = classOf[GoogleApi]
}