As tags video e audio de HTML5 permitem incluir elementos multimídia de maneira padronizada numa página.

<h2>video</h2>

Para incluir um vídeo é necessário utilizar a tag <b>video</b> dentro da qual utilizamos a tag <b>source</b> definindo os parâmetros <b>src</b> com o endereço do vídeo e <b>type</b> com o tipo do vídeo, que pode ser: video/mp4, video/ogg, etc.


<h2>audio</h2>

Para incluir um audio é necessário utilizar a tag <b>audio</b> dentro da qual utilizamos a tag <b>source</b> definindo os parâmetros <b>src</b> com o endereço do audio e <b>type</b> com o tipo do audio, que pode ser: audio/mpeg, audio/ogg, etc.


É necessário também incluir o parâmetro <b>controls</b> ao declarar alguma dessas tags. Como no exemplo a seguir:

	<audio controls>
		<source src="endereço" type="tipo">
	</audio>