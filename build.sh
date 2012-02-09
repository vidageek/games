#! /bin/bash

/home/ubuntu/gradle/bin/gradle test assemble

BUILD=$?

if [ $BUILD -ne 0 ]; then
	exit $BUILD;
fi;

cp build/libs/games-0.1-SNAPSHOT.war ~/artifacts/games/games.war

