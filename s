#! /bin/bash

export JAVA_OPTIONS="-Xmx512m -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=372m" && sbt $@
