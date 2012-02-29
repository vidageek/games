package net.vidageek.games.auth.twitter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Spy;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

public class TwitterAuthProviderTest {
	
	private @Spy ServiceBuilder serviceBuilder = new ServiceBuilder();
	private @Mock OAuthService oAuthService;
	private @Mock Token requestToken;

	private String apiAuthorizeUrl = "https://api.twitter.com/oauth/authorize?oauth_token=4roIG1lwdZWlvJBF2aCwn2m4za2liOD6oeTfQaumN7w";
	@Before
	public void setup() throws Exception {
		initMocks(this);
		expectDatasToServiceBuilder();
	}
	
	private void expectDatasToServiceBuilder() {
		doReturn(oAuthService).when(serviceBuilder).build();
		when(oAuthService.getRequestToken()).thenReturn(requestToken);
		when(oAuthService.getAuthorizationUrl(requestToken)).thenReturn(apiAuthorizeUrl);
	}

	@Test
	public void shouldAutenticateWithTwitterOAuth() {
		String url = new TwitterAuthPovider(serviceBuilder).applicationAuthoritionUrl();
		assertThat(url, equalTo(apiAuthorizeUrl));
	}

}
