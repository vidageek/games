package net.vidageek.games.regex.invariant.infra;

import java.io.File;
import java.net.URL;
import java.util.Scanner;

import net.vidageek.games.regex.invariant.DescriptionInvariantTest;

final public class DescriptionInvariant {

	public void run(final Invariant invariant) throws Throwable {
		URL url = DescriptionInvariantTest.class.getResource("/desc/match.capture.html");
		for (File file : new File(url.toString().substring("file:".length()).replace("match.capture.html", ""))
				.listFiles()) {
			if (file.getAbsolutePath().endsWith("html")) {
				String content = read(file);
				System.out.println("Applying invariant to file " + file.getAbsolutePath());
				invariant.apply(content);
			}
		}
	}

	private String read(final File file) throws Throwable {
		return new Scanner(file).useDelimiter("$$").next();
	}
}
