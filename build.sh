#! /bin/bash

export JAVA_OPTS='-Xmx512m -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=372m -XX:+UseCompressedOops'

BUILD=0

sbt test
let BUILD=$BUILD+$?

sbt gzip-css
let BUILD=$BUILD+$?

sbt gzip-js
let BUILD=$BUILD+$?

sbt package
let BUILD=$BUILD+$?


if [ $BUILD -ne 0 ]; then
       exit $BUILD;
fi;

scp -i /private/vidageek/games.pem target/scala-2.9.2/games* ubuntu@177.71.178.115:~/games.war

