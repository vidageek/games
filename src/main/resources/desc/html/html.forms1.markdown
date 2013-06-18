Os formulários HTML servem para passar informações para o servidor. Eles podem conter caixas de texto,
caixas de senha (password), 
caixas de seleção de múltipla escolha (checkbox), caixas de seleção de escolha única (radio-button), 
botões (button) e
botões de submissão (submit buttons). 

Outros controles possíveis para esse tipo de formularios são: listas de seleção, áreas de texto(textarea),
caixa de agrupamento (fieldset) e etiquetas (labels).

<h3>Formulários</h3>
Um formulário é definido dentro das tags <b><form\></b> e <b></form\></b>, 
ele possui diversos atributos, entre eles destacam-se: <i>action</i>, que especifica o destino 
dos dados do formulário; <i>name</i>, é o nome do formulario; 
finalmente, <i>method</i> indica a forma de envio dos dados(get ou post). 
dentro
desse ambiente é permitido criar os controles que serão descritos a seguir.
Um exemplo de controle é mostrado abaixo:

	<form action="form_resultado.php" method="get">
		Controles
	</form>