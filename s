#! /bin/bash

export JAVA_OPTIONS="-Xmx512m -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=256m" && sbt $@
