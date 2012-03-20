package net.vidageek.games.auth

import java.util.Collection

import scala.collection.JavaConversions.collectionAsScalaIterable
import scala.collection.mutable.Map

class Providers(authProviders: Collection[AuthProvider]) {
  val providers: Map[String, AuthProvider] = Map()
  authProviders.foreach(add(_))

  def add(provider: AuthProvider): Unit = providers += ((provider.name, provider))

  def quantity = providers.size

  def byName(name: String) = providers(name)
}