package vggames.shared.auth

import org.scribe.builder.api.Api

trait AuthProvider extends Serializable {
  def name: String
  def userName: String
  def logout: Unit
  def api: Class[_ <: Api]
}