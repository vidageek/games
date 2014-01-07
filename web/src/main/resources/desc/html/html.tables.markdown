Tabelas em HTML são formadas através das tags <b><table\></table\></b>. No entanto, ao contrário de outros elementos,
em tabelas é preciso definir os níveis inferiores, utilizando as tags <b><tr\></tr\></b> para linhas e <b><td\></td\></b> 
para as células dentro dessa linha, conforme o exemplo abaixo:

	<table border="1">
		<tr>
			<td>Linha 1, coluna 1</td>
			<td>Linha 1, coluna 2</td>
		</tr>
		<tr>
			<td>Linha 2, coluna 1</td>
			<td>Linha 2, coluna 2</td>
		</tr>
	</table>

	
<table border="1">
	<tr>
		<td>Linha 1, coluna 1</td>
		<td>Linha 1, coluna 2</td>
	</tr>
	<tr>
		<td>Linha 2, coluna 1</td>
		<td>Linha 2, coluna 2</td>
	</tr>
</table><br/>

Por padrão, as bordas das tabelas não aparecem. Para fazer com que apareçam, devemos definir o
atributo <b>border</b> como "1".