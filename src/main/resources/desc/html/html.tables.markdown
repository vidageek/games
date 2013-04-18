Tabelas em HTML são formadas através das tags <table\></table\>. No entanto, ao contrário de outros elementos,
em tabelas é preciso definir os níveis inferiores, utilizando as tags <tr\></tr\> para linhas e <td\></td\> 
para as células dentro dessa linha, conforme o exemplo abaixo:

	<table border=1>
		<tr>
			<td>Linha 1, coluna 1</td>
			<td>Linha 1, coluna 2</td>
		</tr>
		<tr>
			<td>Linha 2, coluna 1</td>
			<td>Linha 2, coluna 2</td>
		</tr>
	</table>

	
<table border=1>
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
atributo `border` como "1".

Para mesclar células, utiliza-se os atributos "colspan" (para mesclar horizontalmente) e "rowspan" (para 
mesclar verticalmente). Exemplo:

	<table border=1>
		<tr>
			<td>Linha 1, coluna 1</td>
			<td colspan=2>Linha 1, coluna 1</td>
		</tr>
		<tr>
			<td rowspan=2>Linha 2, coluna 1</td>
			<td>Linha 2, coluna 2</td>
			<td>Linha 2, coluna 2</td>
		</tr>
		<tr>
			<td>Linha 2, coluna 1</td>
			<td>Linha 2, coluna 2</td>
		</tr>
	</table>

<table border=1>
	<tr>
		<td>Linha 1, coluna 1</td>
		<td colspan=2>Linha 1, coluna 1</td>
	</tr>
	<tr>
		<td rowspan=2>Linha 2, coluna 1</td>
		<td>Linha 2, coluna 2</td>
		<td>Linha 2, coluna 2</td>
	</tr>
	<tr>
		<td>Linha 2, coluna 1</td>
		<td>Linha 2, coluna 2</td>
	</tr>
</table>


