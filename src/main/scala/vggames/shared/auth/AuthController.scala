package vggames.shared.auth

import br.com.caelum.vraptor.{Result, Resource, Get}
import vggames.shared.UserHost

@Resource
class AuthController(result : Result, authenticate: StartAuthentication) {

  @Get(Array("/auth/provider/{provider}"))
  def provider(provider : String, backUrl : String) {
    result.include(backUrlParamName, backUrl)
    result.include(authenticationParamName, authenticate.withA(provider))
    result.redirectTo(authenticationWith.authorizationUrl)
  }

  @Get(Array("/authorization/provider/{provider}"))
  def authorization(provider: String, oauth_token : String, oauth_verifier : String) {
    result.redirectTo(backUrl)
  }



  def logout {
//    player.logout
    result.redirectTo(classOf[UserHost]).home;
  }

  private def backUrl: String = result.included().get(backUrlParamName).toString

  private def backUrlParamName = "backUrl"

  private def authenticationWith = result.included().get(authenticationParamName).asInstanceOf[AuthenticateWithProvider]

  private def authenticationParamName = "authenticationWith"

}