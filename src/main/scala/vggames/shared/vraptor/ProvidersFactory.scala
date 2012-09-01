package vggames.shared.vraptor

import br.com.caelum.vraptor.ioc.{ComponentFactory, Component, ApplicationScoped}
import vggames.shared.auth.twitter.TwitterAuthProvider
import vggames.shared.auth.{Providers, AuthProvider}
import javax.servlet.http.HttpServletRequest
import com.google.inject.Provider
import java.net.URL

@Component
@ApplicationScoped
class ProvidersFactory(secrets : OAuthSecrets, request: Provider[HttpServletRequest]) extends ComponentFactory[Providers] {
  implicit def requestRequestedHost(request: Provider[HttpServletRequest]) = new {
    def url: String = {
      val url = new URL(request.get().getRequestURL.toString)
      "%s://%s".format(url.getProtocol, url.getAuthority)
    }
  }

  def getInstance : Providers = {
    new Providers(List[AuthProvider](new TwitterAuthProvider(secrets, request.url + "/authorization/provider")))
  }
}
