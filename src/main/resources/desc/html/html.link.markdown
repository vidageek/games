

Para criar links para outras páginas da internet, existe a tag <b><a\></b>.
Essa tag possui um atributo obrigatório chamado "href" que indica a página a qual o link se refere.<br/>
Para dar um nome a esse link coloca-se a palavra ou o texto entre a tag de abertura e fechamento.
Por exemplo:

	<a href="http://www.vidageek.net">Este é um link para o site do VidaGeek</a>

O resultado da tag acima é:<br/>
<a href="http://www.vidageek.net">Este é um link para o site do VidaGeek</a>


Para criar um link para um outro local da mesma página podemos definir o atributo "name" com o termo identificador 
no local que será referenciado e usamos o símbolo `#` junto com o termo no link, veja o exemplo:

	<a href="#local">Link que vai para o local</a>
	<a name="local">Local para onde o link vai</a>
	
O resultado do código acima é:<br/>

<a href="#local">Link que vai para o local</a><br/>
<a name="local">Local para onde o link vai</a>
	
