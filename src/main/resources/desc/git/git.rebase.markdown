Outra forma de mover commits de um branch para o outro é o **Rebase**.

O **Rebase** funciona de forma muito semelhante ao **Merge**. A diferença é a ordem em que
os commits são aplicados.

No **Merge**, os commits do outro branch são aplicados por cima dos commits do branch atual.

No **Rebase**, os seus commits (acima da base) são **temporariamente** apagados, o branch atual
fica exatamente igual ao outro branch e seus commits são aplicados **um a um** no branch atual.

Uma das vantagens disso é que dessa forma, você consegue manter um histórico organizado. 

Para fazer o **Rebase**, você usa o comando `git rebase BRANCH_DE_ORIGEM`.

Exemplo:

* `git master work` moverá todos os commits (acima da base) da branch **master** para a branch atual, mas abaixo dos seus commits acima da base.

