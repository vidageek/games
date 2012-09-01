package vggames.shared.auth

import org.scribe.model.Verifier
import org.scribe.builder.api.Api

class FixtureAuthProvider extends AuthProvider {
  def applicationAuthorizationUrl = null

  def name = null

  def accessToken(verifier: Verifier) {}

  def userName = null

  def logout {}

  def api = classOf[Api]
}
