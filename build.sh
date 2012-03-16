#! /bin/bash

gradle -i test assemble

BUILD=$?

if [ $BUILD -ne 0 ]; then
       exit $BUILD;
fi;

scp -i /private/games.pem build/libs/games-0.1-SNAPSHOT.war ubuntu@games.vidagek.net:~/games.war

