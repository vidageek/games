package net.vidageek.games.auth

import br.com.caelum.vraptor.Get
import br.com.caelum.vraptor.Resource
import br.com.caelum.vraptor.Result
import net.vidageek.games.UserHost

@Resource
class AuthController(result: Result, providers: Providers, player: Player) {

  @Get(Array("/auth/provider/{name}"))
  def provider(name: String) {
    player.provider = providers.byName(name)
    result.redirectTo(player.provider.applicationAuthoritionUrl)
  }

  @Get(Array("/authorization", "/authorization/"))
  def authorization(oauth_token: String, oauth_verifier: String) {
    player.authorize(AuthorizationVerifier(oauth_verifier))
    result.redirectTo(classOf[UserHost]).home
  }

  def logout {
    player.logout
    result.redirectTo(classOf[UserHost]).home
  }
}