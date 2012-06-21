#! /bin/bash

export JAVA_OPTIONS="-XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=256m" && sbt $@
