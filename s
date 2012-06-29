#! /bin/bash

export JAVA_OPTS="-Xmx512m -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=372m -XX:+UseCompressedOops" && sbt $@
