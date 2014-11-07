#! /bin/bash

[ "${TRAVIS_PULL_REQUEST}" = "true" ] && exit 0

echo "Fazendo backup do WAR"
ssh -o StrictHostKeyChecking=no -i keys/travis_deploy ubuntu@games.vidageek.net "cp ~/jetty/webapps/games.war ~/games.war.bkp"

echo "Copiando o War para servidor remoto"
scp -o StrictHostKeyChecking=no -i keys/travis_deploy web/target/scala-2.11/games-web* ubuntu@games.vidageek.net:~/games.war

DEPLOY="echo Parando Jetty"
DEPLOY="$DEPLOY && /bin/bash ~/jetty/bin/jetty.sh stop"
DEPLOY="$DEPLOY && echo Fazendo backup do Banco"
DEPLOY="$DEPLOY && cp ~/jetty/games.db ~/games.db.bkp"
DEPLOY="$DEPLOY && echo Copiando war"
DEPLOY="$DEPLOY && cp ~/games.war ~/jetty/webapps/."
DEPLOY="$DEPLOY && echo Subindo Jetty"
DEPLOY="$DEPLOY && /bin/bash ~/jetty/bin/jetty.sh start"
DEPLOY="$DEPLOY && echo Deploy Concluído"

ssh -o StrictHostKeyChecking=no -i keys/travis_deploy ubuntu@games.vidageek.net "$DEPLOY"


