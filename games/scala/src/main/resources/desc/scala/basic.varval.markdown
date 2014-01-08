Variáveis em Scala são definidas com a palavra chave `var`. Por exemplo, o seguinte
código declara uma nova variável chamada `nome`, com o valor `joão`:

<pre>var nome = "joão"</pre>

Como vimos acima, usamos o sinal de igualdade (`=`) para fazer a atribuição de um valor
à variável. Podemos, em seguida, alterar o valor de tal variável, usando novamente o `=`.
Veja o exemplo abaixo:

<pre>var idade = 99
idade = 18</pre>

No entanto, essa não é a única forma de declarar variáveis. Uma outra forma é declarar
valores, também conhecidos como constantes. Como o nome diz, tais valores são constantes,
ou seja, uma vez declarados, não mudam mais.

Para declarar constantes em Scala usamos a palavra chave `val`. O código a seguir define
uma constante `idade`, com o valor `19`. Note que ainda estamos usando o sinal `=`
para fazer a atribuição.

<pre>val idade = 19</pre>

O importante aqui é que, como mencionamos acima, a partir do momento em que a constante
está declarada e inicializada, não podemos mais mudá-la. A segunda linha do código abaixo
irá causar um erro de compilação:

<pre>val nome = "maria"
nome = "joão" // tentativa de reatribuir o valor na constante</pre>
