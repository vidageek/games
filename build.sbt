
scalaVersion in ThisBuild := "2.10.2"

resolvers ++= Seq (
    "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
    "twitter-repo" at "http://maven.twttr.com/"
)

EclipseKeys.withSource := true

EclipseKeys.relativizeLibs := false


