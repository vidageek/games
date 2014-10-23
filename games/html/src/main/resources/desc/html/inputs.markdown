Os inputs são a parte de um formulário com a qual o usuário efetivamente interage.

Existem diversos tipos de inputs, dependendo do que você quer que o usuário digite:

- `<input type="text">`: Esse é o input padrão para texto e um dos mais usados. Ele também 
aceita o atributo `value` para vir pré-preenchido e o atributo `placeholder`, para que ele
tenha um texto explicativo até o momento que o usuário move o cursor para o input.
- `<input type="email">`: Utilizado para que o usuário digite o email dele.
- `<input type="password">`: Utilizado para que o usuário digite a senha dele.
- `<input type="submit">`: Será o botão que o usuário deverá clicar para enviar o 
formulário para o servidor. Ele também aceita o atributo `value`, que define o texto do botão.

Todos os inputs também podem ter o atributo `name`, que é como o servidor saberá de qual input 
que os dados vieram.

Caso você queira colocar uma área para o usuário digitar o texto, você pode usar a tag 
`<textarea>`:

- `<textarea></textarea>`: Cria uma área para o usuário digitar o texto. Ele aceita os atributos
`rows` (que determina o número de linhas visíveis) e o `cols` (que determina o número de colunas
visíveis).

Ex.:

    <form action="http://www.google.com/search" method="get">
        <input type="text" name="s" placeholder="Digite a sua busca">
    </form>
