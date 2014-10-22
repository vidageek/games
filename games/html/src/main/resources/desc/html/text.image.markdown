Adicionar uma imagem em uma página html também é muito simples, mas precisa de um 
pouco de cuidado.

Quando adicionamos uma imagem usando html, essa imagem não pode ser apenas decorativa. 
Ela precisa possuir significado dentro da página (como ajudar a elucidar um ponto ou
apresentar informação que é muito difícil de ser descrita, como a foto de uma pessoa
ou lugar).

Além disso, é muito importante colocar uma descrição da foto no atributo `alt` da tag, 
pois isso permite que deficientes visuais também utilizem a sua página com o auxílio de 
[leitores de tela][1] e permite que ferramentas de busca entendam o que aquela imagem 
representa.

A tag imagem fica da seguinte forma em html (Note que você não deve fechá-la):
	<img src="endereço da imagem" alt="texto alternativo da imagem">

Ex.:
- Logo do Html5
		<img src="/static/html/html5.png" alt="Logo do Html5">



[1]: https://translate.google.com/translate?sl=auto&tl=pt&js=y&prev=_t&hl=en&ie=UTF-8&u=https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FScreen_reader&edit-text=&act=url
