Imagine que agora gostariamos de, por exemplo, pegar parte de um 
texto reconhecido por nossa RegEx, e o substituir por outras partes. Na maioria das 
vezes desejamos pegar parte do nosso texto e fazer manipulações sobre ele. Isso essencialmente é algo que dá bastante sentido 
para se utilizar RegEx, somos capazes de fazer buscas de padrões complexos e fazer algum tipo de 
processamento.

Para isso a RegEx tem um recurso chamado de **grupos de captura**.

Para definir um grupo de captura você utiliza os caracteres`(`	e `)` envolvendo 
o **padrão de RegEx** que você que capturar no seu texto.

Exemplo:
* A RegEx `Assunto: (\w+)` **reconhece** o padrão `Assunto: importante` e **captura** `importante` no grupo `1`.
* A RegEx `(parametro=(\w+))` **reconhece** o padrão `parametro=legal` e **captura** `parametro=legal` no grupo `1` e `legal` no grupo `2`.
