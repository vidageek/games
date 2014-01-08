Para facilitar com que outras pessoas utilizem e colaborem com o seu código, uma 
operação muito comum é enviar as suas modificações para um branch em um servidor 
remoto (em geral chamado de **origin**)

Esse servidor remote pode ser um simples repositório Git em uma máquina acessível
a todos os membros do time.

Para enviar os commits do seu branch atual para o servidor remoto você usa o comando
 `git push REPO_REMOTO BRANCH`.
 
 É muito importante que a base do seu branch atual seja o último commit que está
 no repositório remoto. Se isso não acontecer, seu **Push** será negado.

Exemplo:

* `git push origin master` envia os commits do seu branch **atual** para o branch **master** do repositório **origin**
