package vggames.shared.auth

import org.scribe.model.{Verifier, Token}

import vggames.shared.vraptor.Secrets
import org.scribe.builder.api.{Api, GoogleApi}

class GoogleAuthProvider(requester: AuthenticatedRequester) extends AuthProvider {
  override def name = "google"

  override def userName : String = ""

  override def logout : Unit = {}

  override def api: Class[_ <: Api] = classOf[GoogleApi]
}