#! /bin/bash

/home/ubuntu/gradle/bin/gradle clean test assemble

BUILD=$?

if [ $BUILD -ne 0 ]; then
	exit $BUILD;
fi;

cp build/libs/games-1.0-SNAPSHOT.war ~/artifacts/games/games.war

