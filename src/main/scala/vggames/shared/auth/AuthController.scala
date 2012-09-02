package vggames.shared.auth

import br.com.caelum.vraptor.{Result, Resource, Get}
import vggames.shared.UserHost

@Resource
class AuthController(result : Result, viseted : BackUrl, authenticate: StartAuthentication) {

  @Get(Array("/auth/provider/{provider}"))
  def provider(provider : String, backUrl : String) {
    viseted.value = backUrl
    // TODO: Needs Persist the AuthenticateWithProvider
    result.redirectTo(authenticate.withA(provider).authorizationUrl)
  }

  @Get(Array("/authorization/provider/{provider}"))
  def authorization(provider: String, oauth_token : String, oauth_verifier : String) {
//    player.authorize(AuthorizationVerifier(oauth_verifier))
    result.redirectTo(viseted.value)
  }

  def logout {
//    player.logout
    result.redirectTo(classOf[UserHost]).home;
  }
}