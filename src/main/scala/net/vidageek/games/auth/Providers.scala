package net.vidageek.games.auth

import java.util.Collection

import scala.collection.JavaConversions.collectionAsScalaIterable
import scala.collection.immutable.Map

class Providers(authProviders: Collection[AuthProvider]) {
  var providers: Map[String, AuthProvider] = Map()
  authProviders.foreach(add(_))

  def add(provider: AuthProvider): Unit = providers = providers + ((provider.name, provider))

  def quantity = providers.size

  def byName(name: String) = providers(name)
}