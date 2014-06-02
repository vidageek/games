#! /bin/bash

SBT_JAR=/opt/sbt/sbt-launch-0.13.0.jar

SBT="/opt/jdk/jdk1.7.latest/bin/java -XX:PermSize=372m -Dsbt.log.noformat=true -jar $SBT_JAR"

BUILD=0

set -e

rm -fr target

$SBT compile

$SBT test

$SBT package

echo "Fazendo backup do WAR"
ssh -i /private/vidageek/games.pem ubuntu@177.71.178.115 "cp ~/jetty/webapps/games.war ~/games.war.bkp"

echo "Copiando o War para servidor remoto"
scp -i /private/vidageek/games.pem web/target/scala-2.10/games-web* ubuntu@177.71.178.115:~/games.war

