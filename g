#! /bin/bash -i

export GRADLE_OPTS="-XX:MaxPermSize=128m -Xmx512m -XX:ThreadStackSize=256k -XX:+UseCompressedOops"

gradle $@
