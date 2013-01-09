#! /bin/bash

export JAVA_OPTS="-Xmx512m -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=372m -XX:+UseCompressedOops -Dfile.encoding=UTF8" && sbt $@
