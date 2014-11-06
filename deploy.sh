#! /bin/bash

DEPLOY="echo Parando Jetty"
DEPLOY="$DEPLOY && /bin/bash ~/jetty/bin/jetty.sh stop"
DEPLOY="$DEPLOY && echo Fazendo backup do Banco"
DEPLOY="$DEPLOY && cp ~/jetty/games.db ~/games.db.bkp"
DEPLOY="$DEPLOY && echo Copiando war"
DEPLOY="$DEPLOY && cp ~/games.war ~/jetty/webapps/."
DEPLOY="$DEPLOY && echo Subindo Jetty"
DEPLOY="$DEPLOY && /bin/bash ~/jetty/bin/jetty.sh start"
DEPLOY="$DEPLOY && echo Deploy Concluído"

ssh -i keys/travis_deploy ubuntu@games.vidageek.net "$DEPLOY"


