package vggames.vraptor

import br.com.caelum.vraptor.ioc.ApplicationScoped
import br.com.caelum.vraptor.ioc.Component
import br.com.caelum.vraptor.ioc.ComponentFactory
import vggames.auth.twitter.TwitterAuthProvider
import vggames.auth.AuthProvider
import vggames.auth.Providers

@Component
@ApplicationScoped
class ProvidersFactory(secrets: OAuthSecrets) extends ComponentFactory[Providers] {

  def getInstance: Providers =
    new Providers(List[AuthProvider](new TwitterAuthProvider(secrets)))
}
