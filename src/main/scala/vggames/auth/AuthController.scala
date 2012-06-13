package vggames.auth

import br.com.caelum.vraptor.Get
import br.com.caelum.vraptor.Resource
import br.com.caelum.vraptor.Result
import vggames.UserHost
import javax.servlet.http.HttpServletRequest
import vggames.player.Player

@Resource
class AuthController(result : Result, providers : Providers, player : Player, viseted : BackUrl) {

  @Get(Array("/auth/provider/{name}"))
  def provider(name : String, backUrl : String) {
    viseted.value = backUrl
    player.provider = providers.byName(name)
    result.redirectTo(player.provider.applicationAuthorizationUrl)
  }

  @Get(Array("/authorization", "/authorization/"))
  def authorization(oauth_token : String, oauth_verifier : String) {
    player.authorize(AuthorizationVerifier(oauth_verifier))
    result.redirectTo(viseted.value)
  }

  def logout {
    player.logout
    result.redirectTo(classOf[UserHost]).home;
  }
}