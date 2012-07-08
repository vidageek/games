
resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "twitter-repo" at "http://maven.twttr.com/"

seq(netbeans.NetbeansTasks.netbeansSettings:_*)

EclipseKeys.withSource := true
