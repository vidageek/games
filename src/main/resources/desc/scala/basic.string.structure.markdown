Em Scala, palavras ou frase são representadas através do que é chamado `String`.

Para se criar uma `String`, basta que você escreva o texto que quiser entre duas `"`.
	
* `"azul"` é a `String` que representa a palavra **azul**

Caso seu texto contenha `"`, é melhor utilizar outra forma de criar `String` em scala. Você
envolve o texto em `"""`:

* `"""gato "preto" """` é a `String` que representa o texto **gato "preto"**

É possível fazer muitas coisas com `Strings` em Scala.


* Você pode concatená-las: `"abra " + "kadabra"` vira a `String` `"abra kadabra"`
* Você pode invertê-las: `"atrop".reverse` vira a `String` `"porta"`
* Você pode ver o tamanho dela: `"texto bem longo".length` é `15`
* Você pode compará-las: `"abd" > "abc"` é `true`
* E `Strings` são tão importantes, que você consegue transformar qualquer coisa em `String`: `123.toString` vira a `String` `"123"`

