#! /bin/bash

echo "Building Games"
gradle build

echo "Copiando o War"
scp -i ~/.ssh/games.pem build/libs/games* ubuntu@177.71.178.115:~/games.war

echo "Fazendo deploy"
ssh -i ~/.ssh/games.pem ubuntu@177.71.178.115 "/bin/bash ~/jetty/bin/jetty.sh stop && cp ~/games.war ~/jetty/webapps/. && /bin/bash ~/jetty/bin/jetty.sh start"
