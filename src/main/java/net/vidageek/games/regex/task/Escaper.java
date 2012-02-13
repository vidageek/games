package net.vidageek.games.regex.task;

import static com.google.common.collect.Lists.transform;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class Escaper implements Function<String, String> {
	public Set<String> cannotScapePatterns = Sets.newHashSet(" ");
	public Set<String> shouldScapePatterns = Sets.newHashSet("(?:(.*)(\\s)(.*))+");
	public Map<String, String> fromToCharacterReplace = Maps.newConcurrentMap();

	public Escaper() {
		fillShouldMapChar();
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

	public List<String> all(List<String> words) {
		return transform(words, this);
	}

	public String apply(String input) {
		return scape(input) ? applyScapes(input) : input;
	}

	private String applyScapes(String input) {
		String aRegex = thatMatchWith(input);
		Pattern compiledRegex = Pattern.compile(aRegex);
		Matcher matcher = compiledRegex.matcher(input);
		String result = "";
		while (matcher.find()) {
			result += matcher.replaceAll(matcher.group(1) + fromToCharacterReplace.get(matcher.group(2))
					+ matcher.group(3));
		}
		return result;
	}

	private boolean scape(String input) {
		String aRegex = thatMatchWith(input);
		return aRegex != null && shouldScape(input, aRegex);
	}

	private boolean shouldScape(String input, String withRegex) {
		Matcher matchWith = Pattern.compile(withRegex).matcher(input);
		while (matchWith.find()) {
			for (int i = 1; i <= matchWith.groupCount(); i++) {
				if (cannotScapePatterns.contains(matchWith.group(i))) {
					return false;
				}
			}
		}
		return true;
	}

	private String thatMatchWith(String input) {
		for (String aRegex : shouldScapePatterns) {
			if (input.matches(aRegex)) {
				return aRegex;
			}
		}
		return null;
	}
}