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
	private final Set<String> shouldScapePatterns = Sets.newHashSet("(?:(.*)(\\s)(.*))+");
	private final Map<String, String> fromToCharacterReplace = Maps.newConcurrentMap();

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
		fromToCharacterReplace.put(" ", "-Espa&ccedil;o-");
	}

	public List<String> applyAll(final List<String> words) {
		return transform(words, this);
	}

	public String apply(final String word) {
		if ("".equals(word)) {
			return "-Vazio-";
		}
		return scape(word) ? applyScapes(word) : word;
	}

	private String applyScapes(final String input) {
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

	private boolean scape(final String input) {
		String aRegex = thatMatchWith(input);
		return aRegex != null;
	}

	private String thatMatchWith(final String input) {
		for (String aRegex : shouldScapePatterns) {
			if (input.matches(aRegex)) {
				return aRegex;
			}
		}
		return null;
	}

}