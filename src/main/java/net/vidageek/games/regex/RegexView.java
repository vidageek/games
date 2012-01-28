package net.vidageek.games.regex;

import java.util.Scanner;

import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.View;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class RegexView implements View {

	private final HttpServletResponse response;

	public RegexView(final HttpServletResponse response) {
		this.response = response;
	}

	public void render(final String file) {
		try {
			response.setContentType("text/html");
			if (file.endsWith(".html")) {
				response.getWriter().append(read(file));
			}
		} catch (Exception e) {
			throw new RuntimeException("failed to render view.", e);
		}
	}

	private String read(final String file) {
		return new Scanner(RegexView.class.getResourceAsStream(file)).useDelimiter("$$").next();
	}

}
