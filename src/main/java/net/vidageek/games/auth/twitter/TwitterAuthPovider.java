package net.vidageek.games.auth.twitter;

import net.vidageek.games.auth.AuthProvider;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

public class TwitterAuthPovider implements AuthProvider {

	private OAuthService twitterService;
	private Token requestToken;

	public TwitterAuthPovider(ServiceBuilder serviceBuilder) {
		twitterService = serviceBuilder.provider(new TwitterApi()).apiKey("SElUiMMcN2fk7L8T7aEdw")
				.apiSecret("qrPAdS00UvWRWGYdhd3rzxxkJpg213bwGKgbjyX5U").callback("http://games.vidageek.net").build();
		requestToken = twitterService.getRequestToken();
	}

	public String applicationAuthoritionUrl() {
		return twitterService.getAuthorizationUrl(requestToken);
	}

	public String name() {
		return "twitter";

	}

}
