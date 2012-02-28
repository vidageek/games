package net.vidageek.games.auth;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItems;

import java.util.Arrays;

import net.vidageek.games.auth.twitter.TwitterAuthPovider;

import org.junit.Before;
import org.junit.Test;
import org.scribe.builder.ServiceBuilder;

public class ProvidersTest {

	private Providers providers;
	private TwitterAuthPovider twitterAuthPovider;
	private AuthProvider aTestAuthProvider;

	@Before
	public void setup() throws Exception {
		twitterAuthPovider = new TwitterAuthPovider(new ServiceBuilder());
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
		assertThat(providers, hasItems(twitterAuthPovider, aTestAuthProvider));
	}

	@Test
	public void shouldFindTheTwitterProvider() {
		assertThat(providers.byName(twitterAuthPovider.name()), equalTo((AuthProvider)twitterAuthPovider));
	}
}
