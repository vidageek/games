
Usamos estruturas condicionais quando queremos executar um trecho de código apenas em
determinados momentos. Se determinada condição for verdadeira, executamos esse trecho,
caso contrário, ele será simplesmente ignorado. A condição usada deverá sempre ter um
resultado boleano. Veja o exemplo abaixo:

<pre>if(idade >= 18) {
  "já pode beber!"
}
// as chaves são opcionais se o corpo tiver apenas
// uma instrução ou expressão
if(idade >= 18) "já pode beber!"</pre>

Também podemos ter um código que será executado sempre que a condição for falsa - para isso,
usamos o famoso `else`. Veja a seguir:

<pre>if(idade >= 18) "já pode beber!"
else "espere mais um pouco antes de começar a beber!"</pre>

Por fim, outro recurso à nossa disposição é a possibilidade de encadear diversos `if`s -
algo que normalmente referenciamos como `else if`.

Imagine que rolamos um dado, se sair 1 ou 2 retornamos `"perdeu"`, se sair 3 ou 4 retornamos
`"empatou"` e no caso de 5 ou 6, `"ganhou"`. A estrutura condicional abaixo teria exatamente
esse resultado:

<pre>if(dado == 1 || dado == 2) "perdeu"
else if(dado == 3 || dado == 4) "empatou"
else "ganhou"</pre>

Repare que em todos os exemplos, o resultado dos `if`s parecem estar soltos. Em Scala,
`if`s são expressões. Isso quer dizer que eles têm resultados, que podemos armazenar
em variáveis ou, até mesmo, retornar diretamente em métodos.

O código abaixo cria uma constante com o resultado da conversação de horas do formato
de 24 horas para 12 horas:

<pre>val horas24 = 21
val horas12 = horas24 % 12  // horas12 terá 9 como valor</pre>
