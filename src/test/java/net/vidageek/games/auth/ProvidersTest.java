package net.vidageek.games.auth;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Arrays;

import net.vidageek.games.auth.twitter.TwitterAuthProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class ProvidersTest {

	private Providers providers;
	private @Mock TwitterAuthProvider twitterAuthPovider;
	private @Mock AuthProvider aTestAuthProvider;

	@Before
	public void setup() throws Exception {
		initMocks(this);
		when(twitterAuthPovider.name()).thenReturn("twitter");
		when(aTestAuthProvider.name()).thenReturn("TestProvider");
		providers = new Providers(Arrays.<AuthProvider>asList(twitterAuthPovider, aTestAuthProvider));
	}

	@Test
	public void shouldHaveTheTwitterProvider() {
		assertThat(providers.quantity(), equalTo(2));
		assertEquals(providers.byName("twitter"), twitterAuthPovider);
		assertEquals(providers.byName("TestProvider"), aTestAuthProvider);
	}

	@Test
	public void shouldFindTheTwitterProvider() {
		assertThat(providers.byName(twitterAuthPovider.name()), equalTo((AuthProvider) twitterAuthPovider));
	}
}
