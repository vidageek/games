
Uma forma simples de se especificar múltiplos padrões que uma RegEx deve reconhecer é;
utilizar o caractere `|` para separá-los.

Exemplo:
* A RegEx `a|b` reconhece o padrão `a` e o `b`
* A RegEx `abc|a` reconhece o padrão `abc` e o `a`
* A RegEx `a|ab|abc` reconhece o padrão `a`, o `ab` e o `abc`
