package net.vidageek.games.auth

import br.com.caelum.vraptor.Get
import br.com.caelum.vraptor.Resource
import br.com.caelum.vraptor.Result

@Resource
class AuthController(result: Result, providers: Providers) {

  @Get(Array("/auth/provider/{name}"))
  def provider(name: String) = result.redirectTo(providers.byName(name).applicationAuthoritionUrl)
}