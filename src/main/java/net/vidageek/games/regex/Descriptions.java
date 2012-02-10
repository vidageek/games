package net.vidageek.games.regex;

import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

@Component
@ApplicationScoped
public class Descriptions {

	private final Map<String, String> descriptions;

	public Descriptions() {
		descriptions = new ConcurrentHashMap<String, String>();
	}

	public String forGroup(final String groupName) {
		String description = descriptions.get(groupName);
		if (description != null) {
			return description;
		}
		final InputStream stream = Descriptions.class.getResourceAsStream("/desc/" + groupName + ".html");
		if (stream == null) {
			description = "No description for group " + groupName;
		} else {
			description = new Scanner(stream).useDelimiter("$$").next();
		}
		descriptions.put(groupName, description);
		return description;
	}

}
