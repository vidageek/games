#! /bin/bash

PEM="/private/vidageek/games.pem"

if [ "$1" == "manual" ]; then
	PEM="~/.ssh/games.pem"
fi

DEPLOY="echo Parando Jetty"
DEPLOY="$DEPLOY && /bin/bash ~/jetty/bin/jetty.sh stop"
DEPLOY="$DEPLOY && echo Fazendo backup do Banco"
DEPLOY="$DEPLOY && cp ~/jetty/games.db ~/games.db.bkp"
DEPLOY="$DEPLOY && echo Copiando war"
DEPLOY="$DEPLOY && cp ~/games.war ~/jetty/webapps/."
DEPLOY="$DEPLOY && echo Subindo Jetty"
DEPLOY="$DEPLOY && /bin/bash ~/jetty/bin/jetty.sh start"
DEPLOY="$DEPLOY && echo Deploy Concluído"

ssh -i $PEM ubuntu@177.71.178.115 "$DEPLOY"


