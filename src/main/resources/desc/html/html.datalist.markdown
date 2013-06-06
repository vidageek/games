Uma das maneiras de facilitar a inclusão de dados em formulários
é utilizando a tag <b>datalist</b>, que inclui uma lista de opções
com que o campo pode ser preenchido.

Observe o código a seguir:
	<input list="frutas">
	
	<datalist id="frutas">
	  <option value="Maçã">
	  <option value="Banana">
	  <option value="Mamão">
	  <option value="Limão">
	  <option value="Laranja">

	</datalist>

Ao preencher a caixa de texto gerada pelo código, o usuário receberá
sugestões de opções para preencher o campo.

<input list="frutas">

<datalist id="frutas">
  <option value="Maçã">
  <option value="Banana">
  <option value="Mamão">
  <option value="Limão">
  <option value="Laranja">

</datalist>
