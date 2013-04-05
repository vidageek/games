Um recurso muito poderoso de classes de caracteres é a possibilidade de definir classes 
que não contenham determinados caracteres.

Para fazer isso, você acrescenta um `^` logo após o `[` que marca o 
começo da classe de caracteres.

Exemplo:
* A RegEx `[^a]` reconhece qualquer caractere, exceto `a`.
* A RegEx `[^b-d]` reconhece qualquer caractere, exceto `b`, `c` e `d`.
* A RegEx `[^\d]` reconhece qualquer caractere, exceto `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8` e `9`.   

Também existem classes de caracteres opostas pré-definidas:
* A classe `\D` é o oposto de `\d`, ou seja, equivalente à `[^0-9]`
* A class `\S` é o oposto de `\s`, ou seja, equivalente à `[^ \t\n\x0B\f\r]`
* A classe `\W` é o oposto de `\w`, ou seja, equivalente à `[^a-zA-Z_0-9]`

Exemplo:
* A RegEx `\D` reconhece qualquer caractere que não seja `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8` e `9`.
	