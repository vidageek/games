Caso você queira colocar uma área para o usuário digitar o texto, você pode usar a tag 
`<textarea>`:

- `<textarea></textarea>`: Cria uma área para o usuário digitar o texto. Ele aceita os atributos
`rows` (que determina o número de linhas visíveis) e o `cols` (que determina o número de colunas
visíveis).

Além disso, quase sempre queremos mostrar para o usuário o que é para ele digitar
naquele input. Para isso, podemos usar a tag `<label>`. Um atributo importante que
ela tem é o `for`, que você deve colocar o valor do atributo `id` do input que você 
está descrevendo.

Ex.:
	<form>
		<label for="feedback">Feedback</label>
		<textarea id="feedback"></textarea>
	</form>
