package net.vidageek.games.auth

import scala.collection.immutable.Map

class Providers(authProviders: Seq[AuthProvider]) {
  val providers = authProviders.foldLeft(Map[String, AuthProvider]())((m, p) => m + (p.name -> p))

  def quantity = providers.size

  def byName(name: String) = providers(name)
}
