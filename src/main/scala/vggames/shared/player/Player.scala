package vggames.shared.player

import vggames.shared.auth.AuthProvider

case class Player(provider: AuthProvider) {
  def getUserName = provider.userName

  def logout = {
    provider.logout
  }
}