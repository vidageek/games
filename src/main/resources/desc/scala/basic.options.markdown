O tipo Option é útil para evitar referencias null e NullPointerExceptions.
Existem dois subtipos de Option: Some, quando existe um valor e None, quando não existe.
Para cirar estes valores, basta usar o companion object Option(seu_valor)
Uma vez definido um option, além dos métodos deste tipo, pode-se usar diversos recursos da linguagem, como pattern matching e funções de alta ordem sem se preocupar com NullPointerExceptions.
