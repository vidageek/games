package net.vidageek.games.vraptor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

@Component
@ApplicationScoped
final public class OAuthSecrets {

	private final Properties properties;

	public OAuthSecrets() throws IOException {
		properties = new Properties();
		properties.load(new FileInputStream(new File(System.getProperty("user.home") + "/.vgGames/oauth_secrets")));
	}

	public String apiKeyFor(final String providerName) {
		return properties.getProperty(providerName + ".api.key");
	}

	public String apiSecretFor(final String providerName) {
		return properties.getProperty(providerName + ".api.secret");
	}

}
