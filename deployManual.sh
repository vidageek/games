#! /bin/bash

echo "Rebuilding war para evitar Bad Build (sbt incremental falha algumas vezes)"
rm -fr target
./s gzip-css gzip-js package

echo "Fazendo backup do WAR"
ssh -i ~/.ssh/games.pem ubuntu@177.71.178.115 "cp ~/jetty/webapps/games.war ~/games.war.bkp"

echo "Copiando o War para servidor remoto"
scp -i ~/.ssh/games.pem target/scala-2.9.2/games* ubuntu@177.71.178.115:~/games.war

DEPLOY="echo Parando Jetty"
DEPLOY="$DEPLOY && /bin/bash ~/jetty/bin/jetty.sh stop"
DEPLOY="$DEPLOY && echo Fazendo backup do Banco"
DEPLOY="$DEPLOY && cp ~/jetty/games.db ~/games.db.bkp"
DEPLOY="$DEPLOY && echo Copiando war"
DEPLOY="$DEPLOY && cp ~/games.war ~/jetty/webapps/."
DEPLOY="$DEPLOY && echo Subindo Jetty"
DEPLOY="$DEPLOY && /bin/bash ~/jetty/bin/jetty.sh start"
DEPLOY="$DEPLOY && echo Deploy Concluído"

ssh -i ~/.ssh/games.pem ubuntu@177.71.178.115 "$DEPLOY"

