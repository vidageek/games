package vggames.shared.auth

import vggames.shared.vraptor.OAuthSecrets
import br.com.caelum.vraptor.ioc.Component
import javax.servlet.http.HttpServletRequest
import java.net.URL

@Component
case class StartAuthentication(all: Providers, configs: OAuthSecrets, request : HttpServletRequest) {
  implicit def requestRequestedHost(request: HttpServletRequest) = new {
    def url: String = {
      val url = new URL(request.getRequestURL.toString)
      "%s://%s".format(url.getProtocol, url.getAuthority)
    }
  }

  def withA(providerName: String): AutheticatesWithProvider =
    AutheticatesWithProvider(all(providerName), configs, request.url + "/authorization/provider/" + providerName)
}
