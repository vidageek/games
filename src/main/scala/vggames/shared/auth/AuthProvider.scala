package vggames.shared.auth

import org.scribe.model.Verifier
import org.scribe.builder.api.Api

trait AuthProvider extends Serializable {
  def applicationAuthorizationUrl: String
  def name: String
  def accessToken(verifier: Verifier)
  def userName: String
  def logout: Unit
  def api: Class[_ <: Api]
}