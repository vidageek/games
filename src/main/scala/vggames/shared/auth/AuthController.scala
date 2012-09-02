package vggames.shared.auth

import br.com.caelum.vraptor.{Result, Resource, Get}
import vggames.shared.UserHost
import vggames.shared.player.Player

@Resource
class AuthController(result : Result, authenticate: StartAuthentication, all: Providers, session: PlayerSession) {

  @Get(Array("/auth/provider/{provider}"))
  def provider(provider : String, backUrl : String) {
    result.include(backUrlParamName, backUrl)
    result.include(authenticationParamName, authenticate.withA(provider))
    result.redirectTo(authenticationWith.authorizationUrl)
  }

  @Get(Array("/authorization/provider/{provider}"))
  def authorization(provider: String, oauth_token : String, oauth_verifier : String) {
    VerifyPlayerAuthorization(provider, verifyHandler, all, authenticationWith).check(oauth_verifier)
    result.redirectTo(backUrl)
  }



  def logout {
    val player = session.get
    session.remove
    player.logout
    result.redirectTo(classOf[UserHost]).home;
  }

  private def verifyHandler = new HandlerVerificationCheck {
    def ok(a: AuthProvider) {
      session.add(new Player(a))
    }

    def fail {
      session.remove
    }
  }

  private def backUrl: String = result.included().get(backUrlParamName).toString

  private def backUrlParamName = "backUrl"

  private def authenticationWith = result.included().get(authenticationParamName).asInstanceOf[AuthenticateWithProvider]

  private def authenticationParamName = "authenticationWith"

}