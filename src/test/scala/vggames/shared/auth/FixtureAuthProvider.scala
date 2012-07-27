package vggames.shared.auth

import org.scribe.model.Verifier

class FixtureAuthProvider extends AuthProvider {
  def applicationAuthorizationUrl = null

  def name = null

  def accessToken(verifier: Verifier) {}

  def userName = null

  def logout {}
}
