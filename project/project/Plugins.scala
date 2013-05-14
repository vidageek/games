import sbt._

object Plugins extends Build {
  lazy val plugins = Project("root", file("."))
    .dependsOn(uri("git://github.com/guardian/sbt-jasmine-plugin.git#0.8"))
}