package net.vidageek.games.auth;

import org.junit.Test;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

public class TwitterAuthProviderTest {

	@Test
	public void shouldAutenticateWithTwitterOAuth() {
		OAuthService twitterService = new ServiceBuilder().provider(new TwitterApi()).apiKey("SElUiMMcN2fk7L8T7aEdw")
				.apiSecret("qrPAdS00UvWRWGYdhd3rzxxkJpg213bwGKgbjyX5U").callback("http://games.vidageek.net").build();
		Token requestToken = twitterService.getRequestToken();
		String authorizationUrl = twitterService.getAuthorizationUrl(requestToken);
		System.out.println(authorizationUrl);
	}

}
