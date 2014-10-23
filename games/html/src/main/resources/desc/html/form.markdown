Formulários são uma das principais formas de um usuário enviar dados para o servidor.

Para criar um formulário, usamos a tag `<form>`. Mas existem alguns atributos bem importantes
dessa tag:

- **action**: Define a URI para onde o browser irá enviar os dados
- **method**: É o verbo http utilizado para esse envio. Dos 9 verbos definidos pelo protocolo
(GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, CONNECT, PATCH), apenas dois são implementados
pelos browsers. O GET e o POST. A principal diferença entre eles é que o GET é idempotente, ou
seja, se você fizer várias requisições GET (sem que ocorram requisições POST), o resultado 
devolvido deve ser o mesmo. Com o POST, cada requisição nova poderá devolver um novo resultado.
Na prática, isso quer dizer: Se você quer **inserir** dados, use **POST**. Se quer **buscar** 
ou **validar**, user **GET**.

Ex.:

    <form action="http://www.google.com/search" method="get">
    </form>
