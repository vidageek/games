Como sempre trabalhamos com branches, uma operação muito útil é juntar código de branches diferentes.

No Git, uma das formas de se fazer isso é o **Merge** e o comando para fazer o merge é `git merge BRANCH_DE_ORIGEM`.

O **Merge** vai olhar para as duas branches (a atual e a de origem), encontrar a base comum (quais os commits 
que ambas tem) e vai aplicar os commits que a branch atual **não** tem por **cima** dos commits que estão nela.

Caso existam commits na branch atual que não fazem parte da base, ele cria automaticamente um **commit de merge**.

Exemplo:

* `git merge work` moverá todos os commits (acima da base) da branch **work** para a branch atual.

