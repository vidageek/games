Um arquivo HTML é estruturado da seguinte forma:

	<html>
		<head>
			<title>Título da página</title>
		</head>
		
		<body>
			Conteúdo
		</body>
	</html>

Todo o código do arquivo html deve estar entre as tags <b><html\></b> e <b></html\></b>,
o arquivo html é separado em dois grupos, um cabeçalho e o corpo.
O cabeçalho ficam dentro da tag <b><head\></b> e contém as informações do título, das bibliotecas, 
estilos e demais recursos, incluindo
o título da página, que fica dentro da tag <b><title\>Título</title\></b>.

O conteúdo que será mostrado na página fica dentro da tag <b><body\></b>, dentro do corpo
podem ser incluidas as diversas tags de formatação de texto.

A tag DOCTYPE apesar de não obrigatória, é recomendada por razões de compatibilidade, para que o navegador
possa tentar renderizar da forma apropriada. Ela é colocada no começo do arquivo e diz qual é o tipo de documento 
html. O padrão é <!DOCTYPE html> e indica que o documento será do tipo HTML5, que é padrão usado atualmente.

Exemplo:

	<!DOCTYPE html>
	<html>
		<head>
			<title>
			</title>
		</head>
		<body>
		</body>
	</html>

Caso você tenha que trabalhar com páginas mais antigas, é possível que você encontre declarações DOCTYPE para 
tipos diferentes de HTML. Por exemplo, para HTML 4.01 Strict, DOCTYPE é declarado conforme exemplo abaixo:

	<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
