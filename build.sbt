organization := "net.vidageek"

name := "games"

version := "0.1-SNAPSHOT"

scalaVersion := "2.9.1-1"

libraryDependencies ++= Seq(
  "org.prevayler" % "prevayler-factory" % "2.5",
  "com.thoughtworks.xstream" % "xstream" % "1.4.2",
  "log4j" % "log4j" % "1.2.16",
  "org.apache.velocity" % "velocity" % "1.7",
  "javax.servlet" % "jstl" % "1.2",
  "opensymphony" % "sitemesh" % "2.4.2",
  "com.google.guava" % "guava" % "r09",
  "org.scribe" % "scribe" % "1.3.0",
  "com.google.inject" % "guice" % "3.0-rc2",
  "com.google.inject.extensions" % "guice-multibindings" % "3.0-rc2",
  "org.mockito" % "mockito-core" % "1.9.0" % "test",
  "junit" % "junit" % "4.10" % "test",
  "org.specs2" % "specs2_2.9.1" % "1.8.1" % "test",
  "javax.servlet" % "servlet-api" % "2.5" % "provided",
  "br.com.caelum" % "vraptor" % "3.4.0" excludeAll(
    ExclusionRule(organization = "org.springframework")
  )
)

resolvers += "sbt-idea-repo" at "http://mpeltonen.github.com/maven/"

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "0.11.0")



