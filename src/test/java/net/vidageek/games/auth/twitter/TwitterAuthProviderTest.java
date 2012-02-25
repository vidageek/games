package net.vidageek.games.auth.twitter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.Api;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

public class TwitterAuthProviderTest {
	
	private @Mock ServiceBuilder serviceBuilder;
	private @Mock OAuthService oAuthService;
	private @Mock Token requestToken;

	private String apiAuthorizeUrl = "https://api.twitter.com/oauth/authorize?oauth_token=4roIG1lwdZWlvJBF2aCwn2m4za2liOD6oeTfQaumN7w";
	@Before
	public void setup() throws Exception {
		initMocks(this);
		expectDatasToServiceBuilder();
	}
	
	private void expectDatasToServiceBuilder() {
		when(serviceBuilder.provider(Mockito.<Api>any())).thenReturn(serviceBuilder);
		when(serviceBuilder.apiKey( anyString() )).thenReturn(serviceBuilder);
		when(serviceBuilder.apiSecret( anyString() )).thenReturn(serviceBuilder);
		when(serviceBuilder.callback( anyString() )).thenReturn(serviceBuilder);
		when(serviceBuilder.build()).thenReturn(oAuthService);
		when(oAuthService.getRequestToken()).thenReturn(requestToken);
		when(oAuthService.getAuthorizationUrl(requestToken)).thenReturn(apiAuthorizeUrl);
	}

	@Test
	public void shouldAutenticateWithTwitterOAuth() {
		String url = new TwitterAuthPovider(serviceBuilder).applicationAuthoritionUrl();
		assertThat(url, equalTo(apiAuthorizeUrl));
	}

}
