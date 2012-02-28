package net.vidageek.games.auth;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
final public class AuthController {

	private final Result result;
	private final Providers providers;

	public AuthController(Result result, Providers providers) {
		this.result = result;
		this.providers = providers;
		
	}
	
	@Get("/auth/provider/{name}")
	public void provider(String name) {
		result.redirectTo(providers.byName(name).applicationAuthoritionUrl());
	}
	
}
