
scalaVersion in ThisBuild := "2.10.2"

resolvers ++= Seq (
    "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
    "twitter-repo" at "http://maven.twttr.com/"
)

EclipseKeys.withSource := true

EclipseKeys.relativizeLibs := false

seq(jasmineSettings : _*)

appJsDir <+= baseDirectory { _ / "web" / "src" / "main" / "webapp" / "js" }

appJsLibDir <+= baseDirectory { _ / "web" / "src" / "main" / "webapp" / "js" / "lib" }

jasmineTestDir <+= baseDirectory { _ / "web" / "src" / "test" / "webapp" / "js" }

jasmineConfFile <+= baseDirectory { _ / "web" / "src" / "test" / "webapp" / "js" / "test.dependencies.js" }

(test in Test) <<= (test in Test) dependsOn (jasmine)

