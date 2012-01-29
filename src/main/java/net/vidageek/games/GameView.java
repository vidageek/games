package net.vidageek.games;

import java.util.Scanner;

import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.View;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class GameView implements View {

	private final HttpServletResponse response;

	public GameView(final HttpServletResponse response) {
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
		return new Scanner(GameView.class.getResourceAsStream(file)).useDelimiter("$$").next();
	}

}
