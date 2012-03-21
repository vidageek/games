package net.vidageek.games.vraptor

import br.com.caelum.vraptor.ioc.ApplicationScoped
import br.com.caelum.vraptor.ioc.Component
import br.com.caelum.vraptor.ioc.ComponentFactory
import net.vidageek.games.auth.twitter.TwitterAuthProvider
import net.vidageek.games.auth.AuthProvider
import net.vidageek.games.auth.Providers

@Component
@ApplicationScoped
class ProvidersFactory(secrets: OAuthSecrets) extends ComponentFactory[Providers] {

  def getInstance: Providers =
    new Providers(List[AuthProvider](new TwitterAuthProvider(secrets)))
}
