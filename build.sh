#! /bin/bash

gradle -i test assemble

BUILD=$?

if [ $BUILD -ne 0 ]; then
       exit $BUILD;
fi;

scp -i /private/vidageek/games.pem build/libs/games* ubuntu@177.71.178.115:~/games.war

