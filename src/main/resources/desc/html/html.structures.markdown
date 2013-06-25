Um arquivo HTML é estruturado da seguinte forma:

	<html>
		<head><title>Título da página</title></head>
		<body>Conteúdo</body>
	</html>

Todo o código do arquivo html deve estar entre as tags <b><html\></b> e <b></html\></b>.
O arquivo html é separado em dois grupos: cabeçalho e corpo.
O cabeçalho fica dentro da tag <b><head\></b> e contém as informações do título, das bibliotecas, 
estilos e demais recursos, incluindo
o título da página, que fica dentro da tag <b><title\>Título</title\></b>.

O conteúdo que será mostrado na página fica dentro da tag <b><body\></b>, onde são incluídas 
as diversas tags de formatação de texto.

A tag DOCTYPE, apesar de não ser obrigatória, é recomendada por razões de compatibilidade, para que o navegador
possa renderizá-la de forma apropriada. Ela é colocada no começo do arquivo e diz qual é o tipo de documento 
html. O padrão é <!DOCTYPE html>, indicando que o documento será do tipo HTML5.

Exemplo:

	<!DOCTYPE html>
	<html>
		<head><title></title></head>
		<body></body>
	</html>

Caso você tenha que trabalhar com páginas mais antigas, é possível que você encontre declarações DOCTYPE para 
tipos diferentes de HTML. Por exemplo, para HTML 4.01 Strict, DOCTYPE é declarado conforme exemplo abaixo:

	<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
