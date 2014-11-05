
import com.earldouglas.xsbtwebplugin.{ WebappPlugin, WebPlugin }
import java.io.{ FileOutputStream, FileInputStream, PrintWriter }
import java.util.Scanner
import java.util.zip.{ GZIPOutputStream, GZIPInputStream }
import sbt._
import Keys._
import IO._
import com.gu.SbtJasminePlugin._

object GamesVidageekBuild extends Build {
  import Dependencies._
  import TestDependencies._

  val gamesScalaVersion = "2.11.4"
  
  seq(jasmineSettings: _*)

  lazy val root = Project(
    id = "VidaGeekGames",
    base = file("."),
    aggregate = Seq(web, game, regexGame, gitGame, htmlGame, scalaGame, sqlGame, webdevGame))

  lazy val web = Project(
    id = "games-web",
    base = file("web"),
    settings = (jasmineSettings ++ coreWebSettings ++ deps(xstream, log4j, guice,
      guiceBindings, servletApi, cdiApi, vraptor, slick, sqlite, aws,
      selenium, commonsIo))).
    dependsOn(game, regexGame, gitGame, htmlGame, scalaGame, webdevGame, sqlGame)

  lazy val game = Project(
    id = "games-game",
    base = file("games/game"),
    settings = commonSettings ++ deps(slick, sqlite))

  lazy val regexGame = gameProject("regex")

  lazy val gitGame = gameProject("git")

  lazy val htmlGame = gameProject("html")

  lazy val sqlGame = gameProject("sql")

  lazy val scalaGame = gameProject("scala", akkaActor, scalaReflect, scalaCompiler, akkaTestkit, log4j)
  
  lazy val webdevGame = gameProject("webdev", jgit)

  lazy val commonSettings: Seq[Setting[_]] = Defaults.defaultSettings ++ Seq(
    organization := "net.vidageek",
    version := "0.1-SNAPSHOT",
    scalaVersion := gamesScalaVersion,
    scalacOptions ++= Seq("-encoding", "UTF-8", "-deprecation", "-unchecked", "-g:vars", "-feature", "-language:_"),
    resolvers += "Scalaz" at "http://dl.bintray.com/scalaz/releases/",
    libraryDependencies ++= Seq(junit, specs2, mockito, junitInterface, scalaTags))

  lazy val coreWebSettings: Seq[Setting[_]] = commonSettings ++ WebPlugin.webSettings ++ inConfig(Runtime)(WebappPlugin.webappSettings0) ++ Seq(
    libraryDependencies ++= Seq(jettyWebapp, jettyServlets, jettyJsp, jstl),
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
    val jettyVersion = "9.1.1.v20140108"

    val jettyWebapp = "org.eclipse.jetty" % "jetty-webapp" % jettyVersion % "container"
    val jettyServlets = "org.eclipse.jetty" % "jetty-servlets" % jettyVersion % "container"
    val jettyJsp = "org.eclipse.jetty" % "jetty-jsp" % jettyVersion % "container"
    val servletApi = "javax.servlet" % "servlet-api" % "2.5" % "provided"
    val xstream = "com.thoughtworks.xstream" % "xstream" % "1.4.2"
    val log4j = "log4j" % "log4j" % "1.2.17"
    val velocity = "org.apache.velocity" % "velocity" % "1.7"
    val guice = "com.google.inject" % "guice" % "3.0"
    val guiceBindings = "com.google.inject.extensions" % "guice-multibindings" % "3.0"
    val akkaActor = "com.typesafe.akka" %% "akka-actor" % "2.3.6"
    val slick = "com.typesafe.slick" %% "slick" % "2.1.0"
    val scalaReflect = "org.scala-lang" % "scala-reflect" % gamesScalaVersion
    val scalaCompiler = "org.scala-lang" % "scala-compiler" % gamesScalaVersion
    val sqlite = "org.xerial" % "sqlite-jdbc" % "3.7.2"
    val aws = "com.amazonaws" % "aws-java-sdk" % "1.6.12"
    val scalaTags = "com.scalatags" %% "scalatags" % "0.4.2"
    val specs2 = "org.specs2" %% "specs2" % "2.4.9-scalaz-7.0.6"
    val cdiApi = "javax.enterprise" % "cdi-api" % "1.1-20130918"
    val jstl = "javax.servlet" % "jstl" % "1.2"
    val vraptor = "br.com.caelum" % "vraptor" % "3.5.3" excludeAll (ExclusionRule(organization = "org.springframework"))
    val commonsIo = "commons-io" % "commons-io" % "2.2"
    val jgit = "org.eclipse.jgit" % "org.eclipse.jgit" % "3.3.1.201403241930-r"
  }

  object TestDependencies {
    val selenium = "org.seleniumhq.selenium" % "selenium-firefox-driver" % "2.32.0" % "test"
    val mockito = "org.mockito" % "mockito-core" % "1.9.0" % "test"
    val junit = "junit" % "junit" % "4.11" % "test"
    val akkaTestkit = "com.typesafe.akka" %% "akka-testkit" % "2.3.6" % "test"
    val junitInterface = "com.novocode" % "junit-interface" % "0.8" % "test"
  }
}
