package net.vidageek.games.auth

import org.scribe.model.Token
import org.scribe.model.Verifier

trait AuthProvider {
  def applicationAuthoritionUrl: String
  def name: String
  def accessToken(verifier: Verifier): Token
  def userName: String
  def logout: Unit
}