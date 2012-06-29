#! /bin/bash

SBT_JAR=/opt/local/share/sbt/sbt-launch.jar
SBT_JAR=/opt/sbt/sbt-launch-0.11.3-2.jar

SBT="java -Xmx512m -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=372m -XX:+UseCompressedOops -jar $SBT_JAR"

BUILD=0

$SBT compile
let BUILD=$BUILD+$?

$SBT test
let BUILD=$BUILD+$?

$SBT gzip-css
let BUILD=$BUILD+$?

$SBT gzip-js
let BUILD=$BUILD+$?

$SBT package
let BUILD=$BUILD+$?


if [ $BUILD -ne 0 ]; then
       exit $BUILD;
fi;

scp -i /private/vidageek/games.pem target/scala-2.9.2/games* ubuntu@177.71.178.115:~/games.war

