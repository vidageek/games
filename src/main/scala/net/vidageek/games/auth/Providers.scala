package net.vidageek.games.auth

import java.util.Map
import java.util.Collection
import java.util.Collections

import scala.collection.JavaConversions._

import com.google.common.collect.Maps

class Providers(authProviders : Collection[AuthProvider]) {
    val providers : Map[String, AuthProvider] = Maps.newConcurrentMap()
    authProviders.foreach(add(_))

    def add(provider : AuthProvider) {
        providers.put(provider.name, provider)
    }

    def quantity = providers.size()

    def byName(name : String) = providers.get(name)
}