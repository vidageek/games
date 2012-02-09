#! /bin/bash

scp ~/artifacts/games/games.war ubuntu@177.71.178.115:~/.
ssh ubuntu@177.71.178.115 "/bin/bash ~/jetty/bin/jetty.sh stop && cp ~/games.war ~/jetty/webapps/. && /bin/bash ~/jetty/bin/jetty.sh start"

