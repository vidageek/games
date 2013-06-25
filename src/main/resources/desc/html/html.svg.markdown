Para criar desenhos e imagens com HTML5, pode-se usar a tag <b>svg</b>.
Dentro dessa tag, são colocadas outras tags referentes a elementos gráficos
como círculos, retângulos, triângulos, linhas, polígonos, etc. 

O exemplo a seguir:

	<svg>
	  <circle cx='60' cy='40' r='15' fill='red' />
	  <rect x='90' y='25' width='20' height='30' fill='green' />
	</svg>

Gera o seguinte resultado:

<svg height=70px>
  <circle cx='60' cy='40' r='15' fill='red' />
  <rect x='90' y='25' fill='green' width='20' height='30'/>
</svg>

Ao criar círculos, os atributos <b>cx</b> e <b>cy</b> são usadas
para definir a posição do centro, e o atributo <b>r</b> para definir o raio.
No caso de triângulos, retângulos e quadrados, usa-se os atributos <b>width</b>
e <b>height</b> para demarcar largura e altura, respectivamente.

Em ambos os casos, usa-se os atributos <b>fill</b> para preencher com cores sólidas, 
<b>stroke</b> para definir a cor do contorno da figura e <b>stroke-width</b> para a 
largura do contorno.
