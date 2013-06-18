describe(
		'Dom verifier',
		function() {
			it('checks the output',
					function() {
						expect(
								callVerify("<p>Hello world!</p>",
										"<p>Hello world!</p>")).toEqual([]);
					});

			it('checks error', function() {
				expect(callVerify("<p>Hello world!</p>",
						"<p>Hellooooooo world!</p>")).not.toEqual([]);
			});

			 it('checks the line breaks in answers', function() {
			
			 text = "<table border='1'><tr><td>1</td><td>2</td></tr><tr><td>3</td><td>4</td></tr></table>";
			 spacedText = "<table border='1'>\n<tr>\n<td>1</td>\n<td>2</td>\n</tr>\n<tr>\n<td>3</td>\n<td>4</td>\n</tr>\n</table>\n";
					
			 expect(callVerify(text, spacedText)).toEqual([]);
			 });

			it('parameters in tags', function() {
				expect(
						callVerify("<a href='asdf'>link</a>",
								"<a href='asdf'>link</a>")).toEqual([]);
			});

			it('comparing image tags', function() {
				expect(
						callVerify("<img alt='asdf' src='link'/>",
								"<img alt='asdf' src='link'/>")).toEqual([]);
			});

			it('error in tags', function() {
				expect(callVerify("<a href='link1'>link</a>",
						"<a href='link2'>link</a>")).not.toEqual([]);
			});

			it('paragraph exercise test', function() {
				expect(callVerify("<p>Oi mundo</p>", "<p>Oi mundo</p>"))
						.toEqual([]);
			});

			it(
					'html structure',
					function() {
						expect(
								callVerify(
										"<html><head><title></title></head><body>Oi</body></html>",
										"<html><head><title></title></head><body>Oi</body></html>"))
								.toEqual([]);
					});

			it(
					'html structure',
					function() {
						expect(callVerify(
								"<html><head><title></title></head><body></body></html>",
								"Oi")).not.toEqual([]);
					});

			it(
					'Doctype',
					function() {
						expect(
								callVerify(
										"<!DOCTYPE html><html><head><title></title></head><body></body></html>",
										"<!DOCTYPE html><html><head><title></title></head><body></body></html>"))
								.toEqual([]);
					});

			it(
					'Doctype is missing in user answer and judge said OK',
					function() {
						expect(callVerify(
								"<!DOCTYPE html><html><head><title></title></head><body></body></html>",
								"<html><head><title></title></head><body></body></html>")).not
								.toEqual([]);
					});

			it('comparing image tags with error', function() {
				expect(callVerify("<img alt='asdf' src='linkx'/>",
						"<img alt='asdf' src='link'/>")).not.toEqual([]);
			});

			it('tag without parammeter', function() {
				expect(callVerify("<a href='www.google.com'>Google</a>",
						"<a>Google</a>")).not.toEqual([]);
			});

			it('puts no slash closing tag', function() {
				expect(
						callVerify("<img alt='asdf' src='link'>",
								"<img alt='asdf' src='link'/>")).toEqual([]);
			});

			it('puts several slashes closing tag 1', function() {
				expect(callVerify("<img alt='asdf' src='link'/>",
						"<img alt='asdf' src='link' ////>")).not.toEqual([]);
			});

			it('puts several slashes closing tag 2', function() {
				expect(callVerify("<img alt='asdf' src='link'>",
						"<img alt='asdf' src='link'/////>")).not.toEqual([]);
			});

			it(
					'lists exercise with newlines and spaces',
					function() {
						expect(
								callVerify(
										"<ul>\n<li> Elemento 1 </li>         \n<li> Elemento 2 </li>\n</ul>",
										"<ul>\n<li> Elemento 1 </li>\n<li> Elemento 2 </li>\n</ul>"))
								.toEqual([]);
					});

			it(
					'lists exercise with newlines',
					function() {
						expect(
								callVerify(
										"<ul>\n<li> Elemento 1 </li>\n<li> Elemento 2 </li>\n</ul>",
										"<ul>\n<li> Elemento 1 </li><li> Elemento 2 </li>\n</ul>"))
								.toEqual([]);
					});

			it(
					'svg com dados iguais',
					function() {
						expect(
								callVerify(
										"<svg><circle cx='60' cy='40' r='15' fill='red'/><rect x='90' y='25' width='20' height='30' fill='green' /></svg>",
										"<svg><circle cx='60' cy='40' r='15' fill='red'/><rect x='90' y='25' width='20' height='30' fill='green' /></svg>"))
								.toEqual([]);
					});
			it(
					'svg com parâmetros diferentes',
					function() {
						expect(callVerify(
								"<svg><circle cx='70' cy='10' r='5' fill='red'/><rect x='90' y='25' width='20' height='30' fill='green' /></svg>",
								"<svg><circle cx='60' cy='40' r='15' fill='red'/><rect x='90' y='25' width='20' height='30' fill='green' /></svg>")).not
								.toEqual([]);
					});
			it(
					'tabelas iguais',
					function() {
						expect(
								callVerify(
										"<table border='1'><tr><td>1</td><td>2</td></tr><tr><td>3</td><td>4</td></tr></table>",
										"<table border='1'><tr><td>1</td><td>2</td></tr><tr><td>3</td><td>4</td></tr></table>"))
								.toEqual([]);
					});
			it('texto apos a última tag',
					function() {					
						expect(callVerify("<p>Oi mundo</p>",
								"<p>Oi mundo</p>asdf"))
								.not.toEqual([]);
					});
		});