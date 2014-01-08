Os formulários HTML servem para passar informações para o servidor. Eles podem conter caixas de texto,
 de senha (password), de múltipla escolha (checkbox), de escolha única (radio-button), 
botões (button) e botões de submissão (submit buttons). 

Outros controles possíveis para esse tipo de formulários são: listas de seleção, áreas de texto (textarea),
caixa de agrupamento (fieldset) e etiquetas (labels).

<h3>Formulários</h3>
Um formulário é definido dentro das tags <b><form\></b> e <b></form\></b>, 
e possui diversos atributos, entre eles destacam-se: <i>action</i>, que especifica o destino 
dos dados do formulário; <b>name</b>, é o nome do formulário e <b>method</b>, que indica a forma 
de envio dos dados (get ou post). 
Dentro desse ambiente é possível criar os controles que serão descritos a seguir.
Um exemplo de controle é mostrado abaixo:

	<form action="form_resultado.php" method="get">
		Controles
	</form>