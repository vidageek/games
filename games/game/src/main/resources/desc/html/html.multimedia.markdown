As tags video e audio de HTML5 permitem incluir elementos multimídia de maneira padronizada numa página.

<h2>Vídeo</h2>

Para incluir um vídeo é necessário utilizar a tag <b>video</b> dentro da qual utilizamos a tag <b>source</b> definindo os parâmetros <b>src</b> com o endereço do vídeo e <b>type</b> com o tipo do vídeo, que pode ser: video/mp4, video/ogg, etc.

	<video  width="largura" height="altura" controls>
		<source src="endereço" type="tipo">
	</video>

<h2>Áudio</h2>

Para incluir um áudio é necessário utilizar a tag <b>audio</b> dentro da qual utilizamos a tag <b>source</b> definindo os parâmetros <b>src</b> com o endereço do audio e <b>type</b> com o tipo do áudio: audio/mpeg, audio/ogg, etc.


É necessário também incluir o parâmetro <b>controls</b> ao declarar alguma dessas tags. Como no exemplo a seguir:

	<audio controls>
		<source src="endereço" type="tipo">
	</audio>