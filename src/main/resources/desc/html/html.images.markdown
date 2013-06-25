Para inserir uma imagem em html basta inserir a tag:
	<img src="path" alt="texto" height="altura" width="largura">

Onde o termo <b>path</b> é o caminho para o arquivo da imagem, <b>texto</b> 
é o texto a ser exibido caso a imagem não carregue, <b>altura</b> representa a 
altura para exibição da imagem em pixels, e <b>largura</b> é a largura para 
exibição da imagem em pixels.

Também podemos usar a tag <b>figure</b> aliada à <b>img</b> para 
colocar legendas nas imagens. Observe o exemplo abaixo:
	<figure>
	<img src='http://www.vidageek.net/images/logo.png' alt='logo' />
	<figcaption>Logo do VidaGeek Games</figcaption>
	</figure>

O código gera o seguinte resultado:
<figure>
<img src='http://www.vidageek.net/images/logo.png' width='85px'; alt='logo' />
<figcaption>Logo do VidaGeek Games</figcaption>
</figure>

