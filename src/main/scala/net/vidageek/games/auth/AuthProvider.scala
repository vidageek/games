package net.vidageek.games.auth

trait AuthProvider {
  def applicationAuthoritionUrl: String
  def name: String
}