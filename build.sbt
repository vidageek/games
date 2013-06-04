
resolvers ++= Seq (
    "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
    "twitter-repo" at "http://maven.twttr.com/"
)

EclipseKeys.withSource := true

seq(jasmineSettings : _*)

appJsDir <+= sourceDirectory { src => src / "main" / "webapp" / "js" }

appJsLibDir <+= sourceDirectory { src => src / "main" / "webapp" / "js" / "lib" }

jasmineTestDir <+= sourceDirectory { src => src / "test" / "webapp" / "js" }

jasmineConfFile <+= sourceDirectory { src => src / "test" / "webapp" / "js" / "test.dependencies.js" }

(test in Test) <<= (test in Test) dependsOn (jasmine)

