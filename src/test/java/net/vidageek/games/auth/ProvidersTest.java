package net.vidageek.games.auth;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import net.vidageek.games.auth.twitter.TwitterAuthProvider;
import net.vidageek.games.vraptor.OAuthSecrets;

import org.junit.Before;
import org.junit.Test;
import org.scribe.builder.ServiceBuilder;

public class ProvidersTest {

	private Providers providers;
	private TwitterAuthProvider twitterAuthPovider;
	private AuthProvider aTestAuthProvider;

	@Before
	public void setup() throws Exception {
		twitterAuthPovider = new TwitterAuthProvider(new ServiceBuilder(), new OAuthSecrets());
		aTestAuthProvider = aTestAuthProvider();
		providers = new Providers(Arrays.asList(twitterAuthPovider, aTestAuthProvider));
	}

	private AuthProvider aTestAuthProvider() {
		return new AuthProvider() {

			public String name() {
				return "TestProvider";
			}

			public String applicationAuthoritionUrl() {
				return "http://url-authorization-test";
			}
		};
	}

	@Test
	public void shouldHaveTheTwitterProvider() {
		assertThat(providers.quantity(), equalTo(2));

		// esses asserts substituem o anterior corretamente?
		// assertThat(providers, hasItems(twitterAuthPovider, aTestAuthProvider));
		assertEquals(providers.byName("twitter"), twitterAuthPovider);
		assertEquals(providers.byName("TestProvider"), aTestAuthProvider);
	}

	@Test
	public void shouldFindTheTwitterProvider() {
		assertThat(providers.byName(twitterAuthPovider.name()), equalTo((AuthProvider) twitterAuthPovider));
	}
}
