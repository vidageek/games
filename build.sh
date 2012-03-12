#! /bin/bash

export GRADLE_OPTS="-XX:ThreadStackSize=256k -XX:+UseCompressedOops" && /home/ubuntu/gradle/bin/gradle -i test assemble

BUILD=$?

if [ $BUILD -ne 0 ]; then
	exit $BUILD;
fi;

cp build/libs/games-0.1-SNAPSHOT.war ~/artifacts/games/games.war

