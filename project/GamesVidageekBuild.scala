
import java.io.{ FileOutputStream, FileInputStream, PrintWriter }
import java.util.Scanner
import java.util.zip.{ GZIPOutputStream, GZIPInputStream }
import sbt._
import Keys._
import com.github.siasia._
import WebPlugin._
import WebappPlugin._
import IO._
import PluginKeys._
import org._
import com.gu.SbtJasminePlugin._

object GamesVidageekBuild extends Build {
  import Dependencies._
  import TestDependencies._

  seq(jasmineSettings: _*)

  lazy val root = Project(
    id = "VidaGeekGames",
    base = file("."),
    aggregate = Seq(web, game, regexGame, gitGame, htmlGame, metaGame, scalaGame))

  lazy val web = Project(
    id = "games-web",
    base = file("web"),
    settings = (jasmineSettings ++ coreWebSettings ++ deps(xstream, log4j, jstl, guice,
      guiceBindings, servletApi, jspApi, cdiApi, vraptor, slick, sqlite, aws,
      actuarius, sitemesh, selenium))).
    dependsOn(game, regexGame, gitGame, htmlGame, metaGame, scalaGame)

  lazy val game = Project(
    id = "games-game",
    base = file("games/game"),
    settings = commonSettings ++ deps(actuarius))

  lazy val regexGame = gameProject("regex")

  lazy val gitGame = gameProject("git")

  lazy val htmlGame = gameProject("html")

  lazy val metaGame = gameProject("meta")

  lazy val scalaGame = gameProject("scala", akkaActor, scalaReflect, scalaCompiler, akkaTestkit, log4j)

  lazy val commonSettings: Seq[Setting[_]] = Defaults.defaultSettings ++ Seq(
    organization := "net.vidageek",
    version := "0.1-SNAPSHOT",
    scalacOptions ++= Seq("-encoding", "UTF-8", "-deprecation", "-unchecked", "-g:vars", "-feature", "-language:_"),
    libraryDependencies ++= Seq(junit, specs2, mockito, junitInterface, scalaTags))

  lazy val coreWebSettings: Seq[Setting[_]] = commonSettings ++ webSettings ++ inConfig(Runtime)(webappSettings0) ++ Seq(
    libraryDependencies ++= Seq(jettyWebapp, jettyServlets, jettyJsp, jsp),
    classDirectory in Compile <<= webappDir {
      _ / "WEB-INF" / "classes"
    },
    appJsDir <+= baseDirectory { _ / "src" / "main" / "resources" / "games" / "js" },
    appJsLibDir <+= baseDirectory { _ / "src" / "main" / "webapp" / "js" / "lib" },
    jasmineTestDir <+= baseDirectory { _ / "src" / "test" / "webapp" / "js" },
    jasmineConfFile <+= baseDirectory { _ / "src" / "test" / "webapp" / "js" / "test.dependencies.js" } /*(test in Test) <<= (test in Test) dependsOn (jasmine)*/ )

  lazy val webappDir = baseDirectory { _ / "src" / "main" / "webapp" }

  private def deps(d: ModuleID*): Seq[Setting[_]] = Seq(libraryDependencies ++= d.toSeq)

  private def gameProject(name: String, d: ModuleID*) = Project(
    id = "games-" + name,
    base = file("games/" + name),
    settings = commonSettings ++ deps(d: _*)).
    dependsOn(game)

  object Dependencies {
    val jettyVersion = "7.4.5.v20110725"

    val jettyWebapp = "org.eclipse.jetty" % "jetty-webapp" % jettyVersion % "container"
    val jettyServlets = "org.eclipse.jetty" % "jetty-servlets" % jettyVersion % "container"
    val jettyJsp = "org.eclipse.jetty" % "jetty-jsp-2.1" % jettyVersion % "container"
    val jsp = "org.mortbay.jetty" % "jsp-2.1-glassfish" % "2.1.v20100127" % "container"
    val servletApi = "javax.servlet" % "servlet-api" % "2.5" % "provided"
    val jspApi = "javax.servlet.jsp" % "jsp-api" % "2.1" % "provided"
    val jstl = "javax.servlet" % "jstl" % "1.2"
    val xstream = "com.thoughtworks.xstream" % "xstream" % "1.4.2"
    val log4j = "log4j" % "log4j" % "1.2.17"
    val velocity = "org.apache.velocity" % "velocity" % "1.7"
    val guice = "com.google.inject" % "guice" % "3.0"
    val guiceBindings = "com.google.inject.extensions" % "guice-multibindings" % "3.0"
    val akkaActor = "com.typesafe.akka" %% "akka-actor" % "2.2.3"
    val slick = "com.typesafe.slick" %% "slick" % "1.0.0"
    val scalaReflect = "org.scala-lang" % "scala-reflect" % "2.10.3"
    val scalaCompiler = "org.scala-lang" % "scala-compiler" % "2.10.3"
    val sqlite = "org.xerial" % "sqlite-jdbc" % "3.7.2"
    val aws = "com.amazonaws" % "aws-java-sdk" % "1.6.12"
    val actuarius = "eu.henkelmann" % "actuarius_2.10.0" % "0.2.6"
    val sitemesh = "opensymphony" % "sitemesh" % "2.4.2"
    val scalaTags = "com.scalatags" % "scalatags_2.10" % "0.2.0"
    val specs2 = "org.specs2" %% "specs2" % "2.3.7"
    val cdiApi = "javax.enterprise" % "cdi-api" % "1.1-20130918"
    val vraptor = "br.com.caelum" % "vraptor" % "3.5.3" excludeAll (ExclusionRule(organization = "org.springframework"))
  }

  object TestDependencies {
    val selenium = "org.seleniumhq.selenium" % "selenium-firefox-driver" % "2.32.0" % "test"
    val mockito = "org.mockito" % "mockito-core" % "1.9.0" % "test"
    val junit = "junit" % "junit" % "4.11" % "test"
    val akkaTestkit = "com.typesafe.akka" %% "akka-testkit" % "2.2.3" % "test"
    val junitInterface = "com.novocode" % "junit-interface" % "0.8" % "test"
  }
}
