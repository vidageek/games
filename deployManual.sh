#! /bin/bash

echo "Rebuilding war para evitar Bad Build (sbt incremental falha algumas vezes)"
rm -fr target
./s gzip-assets package

echo "Fazendo backup do WAR"
ssh -i ~/.ssh/games.pem ubuntu@177.71.178.115 "cp ~/jetty/webapps/games.war ~/games.war.bkp"

echo "Copiando o War para servidor remoto"
scp -i ~/.ssh/games.pem web/target/scala-2.9.2/games-web* ubuntu@177.71.178.115:~/games.war


bash deploy.sh manual
