package vggames.shared.vraptor

import br.com.caelum.vraptor.ioc.{ComponentFactory, Component, ApplicationScoped}
import vggames.shared.auth.twitter.TwitterAuthProvider
import vggames.shared.auth.{Providers, AuthProvider}

@Component
@ApplicationScoped
class ProvidersFactory(secrets : OAuthSecrets) extends ComponentFactory[Providers] {

  def getInstance : Providers =
    new Providers(List[AuthProvider](new TwitterAuthProvider(secrets)))
}
