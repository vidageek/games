package net.vidageek.games.auth.twitter;

import net.vidageek.games.auth.AuthProvider;
import net.vidageek.games.vraptor.OAuthSecrets;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

public class TwitterAuthPovider implements AuthProvider {

	private final OAuthService twitterService;
	private final Token requestToken;

	public TwitterAuthPovider(final ServiceBuilder serviceBuilder, final OAuthSecrets secrets) {
		twitterService = serviceBuilder.provider(new TwitterApi()).apiKey(secrets.apiKeyFor("twitter"))
				.apiSecret(secrets.apiSecretFor("twitter")).callback("http://games.vidageek.net").build();
		requestToken = twitterService.getRequestToken();
	}

	public String applicationAuthoritionUrl() {
		return twitterService.getAuthorizationUrl(requestToken);
	}

	public String name() {
		return "twitter";

	}

}
