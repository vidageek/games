package vggames.shared.vraptor

import br.com.caelum.vraptor.ioc.ApplicationScoped
import br.com.caelum.vraptor.ioc.Component
import br.com.caelum.vraptor.ioc.ComponentFactory
import vggames.shared.auth.Providers
import vggames.shared.auth.AuthProvider
import vggames.shared.auth.twitter.TwitterAuthProvider

@Component
@ApplicationScoped
class ProvidersFactory(secrets : OAuthSecrets) extends ComponentFactory[Providers] {

  def getInstance : Providers =
    new Providers(List[AuthProvider](new TwitterAuthProvider(secrets)))
}
