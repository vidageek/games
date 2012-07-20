package vggames.shared.auth

import org.scribe.model.Verifier

trait AuthProvider {
  def applicationAuthorizationUrl: String
  def name: String
  def accessToken(verifier: Verifier)
  def userName: String
  def logout: Unit
}