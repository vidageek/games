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


