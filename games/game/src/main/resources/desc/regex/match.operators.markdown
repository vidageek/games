Dificilmente você encontrará uma RegEx que não utiliza algum operador de repetição. A idéia principal desses
operadores é permitir que você defina um padrão específico que você espera que ocorra um determinado número
de vezes.

Os operadores de repetição são:
* `?` determina que o que vier imediatamente antes dele deve aparecer 0 ou 1 vez na expressão.
* `*` determina que o que vier imediatamente antes dele deve aparecer 0 ou mais vezes na expressão.
* `+` determina que o que vier imediatamente antes dele deve aparecer 1 ou mais vezes na expressão.
* `{n}` determina que o que vier imediatamente antes dele deve aparecer exatamente `n` vezes na expressão.
* `{n,}` determina que o que vier imediatamente antes dele deve aparecer `n` ou mais vezes na expressão.
* `{n, m}` determina que o que vier imediatamente antes dele deve aparecer no mínimo `n` e no máximo `m` vezes na expressão.

Um fato importante é que o que vem antes de um operador de repetição não precisa ser apenas 1 caractere. Pode ser uma classe
de caracteres ou qualquer outra forma de agrupamento.

Exemplos:
* A RegEx `a?` reconhece o padrão `-Vazio-` e `a`
* A RegEx `[a-c]+` reconhece os padrões `a`, `ab`, `ac`, `abc` e vários outros, mas não reconhece `-Vazio-` e `d`
* A RegEx `[abn]{6}` reconhece `banana`, mas não reconhece `banan`
* A RegEx `.*` reconhece os padrões `-Vazio-`, `estrela`, `cama` e infinitos outros.
