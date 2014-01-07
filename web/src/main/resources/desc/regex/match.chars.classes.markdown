Para ser capaz de reconhecer padrões mais complexos que simples sequências de caracteres,
é possível definir **classes de caracteres.** Uma classe de caracteres funciona
como se fosse um caractere capaz de reconhecer vários caracteres diferentes.

Para definir uma classe de caracteres, você utiliza os caracteres `[` e `]` 
envolvendo os caracteres que você quer que as classes de caracteres reconheça.

Exemplo:
* A RegEx `[c9]` **reconhece** o padrão `c` e o padrão `9`.
* A RegEx `[Aa]` **reconhece** o padrão `A` e o padrão `a`.
* A RegEx `[ab]` **reconhece** o padrão `a` e o padrão `b`, mas não reconhece o padrão `ab`, pois este tem 2 caracteres.

Uma classe de caracteres para reconhecer todas as letras maiúsculas seria algo como 
`[ABCDEFGHJKLMNOPQRSTUVWXYZ]`, o que é bem ilegível e talvez você não note que falta algum caractere
(notou que aquela RegEx não reconhece o caractere `I`?). Para resolver esse problema, é possível
criar uma classe de caracteres para uma sequência de caracteres, utilizando o caractere `-`.

Exemplo:
* A RegEx `[A-Z]` **reconhece todas** as letras maiúsculas.   
* A RegEx `[A-Z0-9]` **reconhece todas** as letras maiúsculas e números.
* A RegEx `[f-h]` **reconhece** as letras `f`, `g` e `h`.

Como existem algumas classes de caracteres que são muito comuns, existem algumas classes pré-definidas. São elas:

* A classe `\d` é equivalente à classe [0-9]
* A classe `\s` é equivalente à classe [ \t\n\x0B\f\r]
* A classe `\w` é equivalente à classe [a-zA-Z_0-9]
* A classe `.`  reconhece todos os tipos de caracteres, exceto o `\n`.

Exemplo:
* A RegEx `.` **reconhece** todos os caracteres.
* A RegEx `\d\w` ** reconhece** o padrão `9a` e o `11`, mas não reconhece o padrão `a9`.
