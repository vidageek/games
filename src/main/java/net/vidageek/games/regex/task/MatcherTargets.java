package net.vidageek.games.regex.task;

import static com.google.common.collect.Collections2.transform;
import static java.util.Arrays.copyOfRange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class MatcherTargets implements Iterable<String> {

	private final List<String> matcherTargets = new ArrayList<String>();
	private final Set<String> cannotScapePatterns = Sets.newHashSet(" ");
	private final Set<String> shouldScapePatterns = Sets.newHashSet("(?:(.*)(\\s)(.*))+");
	
	private final Map<String, String> fromToCharacterReplace = Maps.newConcurrentMap();

	private MatcherTargets(String aMatcherTarget, String... othersMatchersTargets) {
		fillShouldMapChar();
		matcherTargets.add(aMatcherTarget);
		matcherTargets.addAll(Arrays.asList(othersMatchersTargets));
	}

	private void fillShouldMapChar() {
		fromToCharacterReplace.put("\t", "\\\\t");
		fromToCharacterReplace.put("\b", "\\\\b");
		fromToCharacterReplace.put("\n", "\\\\n");
		fromToCharacterReplace.put("\r", "\\\\r");
		fromToCharacterReplace.put("\f", "\\\\f");
		fromToCharacterReplace.put("\'", "\\\\'");
		fromToCharacterReplace.put("\\", "\\\\");
	}

	public String showMessages() {
		return "\"" + Joiner.on("\" e \"").join(scapeTarges()) + "\"";
	}

	private Collection<String> scapeTarges() {
		return transform(matcherTargets, new Function<String, String>() {
			public String apply(String input) {
				return scape(input) ? applyScapes(input) : input;
			}

			private String applyScapes(String input) {
				String aRegex = thatMatchWith(input);
				Pattern compiledRegex = Pattern.compile(aRegex);
				Matcher matcher = compiledRegex.matcher(input);
				String result = "";
				while(matcher.find()) {
					result += matcher.replaceAll(matcher.group(1) + fromToCharacterReplace.get(matcher.group(2)) + matcher.group(3));
				}
				return result;
			}

			private boolean scape(String input) {
				String aRegex = thatMatchWith(input);
				return aRegex != null && !cannotScapePatterns.contains(input);
			}

			private String thatMatchWith(String input) {
				for (String aRegex : shouldScapePatterns) {
					if (input.matches(aRegex)) {
						return aRegex;
					}
				}
				return null;
			}

		});
	}

	public Iterator<String> iterator() {
		return Collections.unmodifiableCollection(matcherTargets).iterator();
	}

	public static MatcherTargets fromStrings(String... matchingTargets) {
		return new MatcherTargets(matchingTargets[0], copyOfRange(matchingTargets, 1, matchingTargets.length));
	}

}
