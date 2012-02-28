package net.vidageek.games.auth;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import com.google.common.collect.Maps;

final public class Providers implements Iterable<AuthProvider>{

	private final Map<String, AuthProvider> providers = Maps.newConcurrentMap();
	
	public Providers(Collection<AuthProvider> providers) {
		for (AuthProvider provider : providers) {
			add(provider);
		}
	}

	public void add(AuthProvider provider) {
		providers.put(provider.name(), provider);
	}

	public Integer quantity() {
		return providers.size();
	}

	public Iterator<AuthProvider> iterator() {
		return Collections.unmodifiableCollection(providers.values()).iterator();
	}

	public AuthProvider byName(String name) {
		return providers.get(name);
	}

}
