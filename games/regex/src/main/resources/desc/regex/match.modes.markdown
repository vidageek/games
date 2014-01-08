Imagine que você quer fazer uma RegEx que reconhece `flor`, mas que também reconheça `Flor` e `fLoR`.
Embora `[fF][lL]o[rR]` funcione, ela não é muito elegante.

Para situações como essa, onde você quer que a RegEx não diferencie maiúsculas de minúsculas, você pode colocar `(?i)` na frente dela.

Exemplo:
* A RegEx `(?i)ab` **reconhece** `ab`, `Ab`, `aB` e `AB`

Em algumas outras situações, queremos que o operador `.` também seja capaz de reconhecer `-Quebra-de-Linha-`. Para isso, 
você pode colocar `(?s)` no começo da RegEx.

Exemplo:
* A RegEx `(?s).{1, 3}` **reconhece** o padrão `-Quebra-de-Linha-`, `-Quebra-de-Linha-a`, `8-Quebra-de-Linha-`, entre outros. 

