#! /bin/bash

echo "Fazendo backup do WAR"
ssh -i ~/.ssh/games.pem ubuntu@177.71.178.115 && cp ~/jetty/webapps/games.war ~/games.war.bkp

echo "Copiando o War"
scp -i ~/.ssh/games.pem target/scala-2.9.2/games* ubuntu@177.71.178.115:~/games.war

echo "Fazendo deploy"
ssh -i ~/.ssh/games.pem ubuntu@177.71.178.115 "/bin/bash ~/jetty/bin/jetty.sh stop && cp ~/games.war ~/jetty/webapps/. && /bin/bash ~/jetty/bin/jetty.sh start"
