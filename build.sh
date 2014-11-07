#! /bin/bash

set -e

sbt test package

echo "Fazendo backup do WAR"
ssh -i keys/travis_deploy ubuntu@games.vidageek.net "cp ~/jetty/webapps/games.war ~/games.war.bkp"

echo "Copiando o War para servidor remoto"
scp -i keys/travis_deploy web/target/scala-2.11/games-web* ubuntu@games.vidageek.net:~/games.war

