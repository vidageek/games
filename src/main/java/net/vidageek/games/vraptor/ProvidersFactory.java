package net.vidageek.games.vraptor;

import java.util.Arrays;
import java.util.List;

import net.vidageek.games.auth.AuthProvider;
import net.vidageek.games.auth.Providers;
import net.vidageek.games.auth.twitter.TwitterAuthPovider;

import org.scribe.builder.ServiceBuilder;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@Component
@ApplicationScoped
final public class ProvidersFactory implements ComponentFactory<Providers> {

	private final OAuthSecrets secrets;

	public ProvidersFactory(final OAuthSecrets secrets) {
		this.secrets = secrets;
	}

	public Providers getInstance() {
		List<AuthProvider> providers = Arrays.<AuthProvider> asList(new TwitterAuthPovider(new ServiceBuilder(),
				secrets));
		return new Providers(providers);
	}

}
