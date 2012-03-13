package net.vidageek.games.vraptor

import java.util.Arrays

import org.scribe.builder.ServiceBuilder

import br.com.caelum.vraptor.ioc.ApplicationScoped
import br.com.caelum.vraptor.ioc.Component
import br.com.caelum.vraptor.ioc.ComponentFactory
import net.vidageek.games.auth.twitter.TwitterAuthProvider
import net.vidageek.games.auth.AuthProvider
import net.vidageek.games.auth.Providers

@Component
@ApplicationScoped
class ProvidersFactory(secrets: OAuthSecrets) extends ComponentFactory[Providers] {

  def getInstance: Providers = {
    val providers = Arrays.asList[AuthProvider](new TwitterAuthProvider(secrets))
    new Providers(providers)
  }
}