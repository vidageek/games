import java.io.{FileOutputStream, FileInputStream, PrintWriter}
import java.util.Scanner
import java.util.zip.{GZIPOutputStream, GZIPInputStream}
import sbt._
import Keys._
import com.github.siasia._
import WebPlugin._
import WebappPlugin._
import IO._
import PluginKeys._
import org._

object GamesVidageekBuild extends Build {

  import Dependencies._
  import Assets._

  lazy val root = Project(
    id = "VidaGeekGames",
    base = file("."),
    aggregate = Seq(web, game, regexGame, gitGame, htmlGame, metaGame, scalaGame))
    
  lazy val web = Project(
    id = "games-web",
    base = file("web"),
    settings = (tasks ++ coreWebSettings ++ deps(xstream, log4j, jstl, guice, 
        guiceBindings, servletApi, jspApi, vraptor, slick, sqlite, aws, 
        actuarius, sitemesh, selenium)
    )).
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
    scalaVersion := "2.10.0",
    scalacOptions ++= Seq("-encoding", "UTF-8", "-deprecation", "-unchecked", "-g:vars", "-feature", 
        "-language:_"),
    libraryDependencies ++= Seq(junit, specs2, mockito, junitInterface, scalaTags))

  lazy val coreWebSettings: Seq[Setting[_]] = commonSettings ++ webSettings ++ inConfig(Runtime)(webappSettings0) ++ Seq(
    libraryDependencies ++= Seq(jettyWebapp, jettyServlets, jettyJsp, jsp),
    classDirectory in Compile <<= webappDir {
      _ / "WEB-INF" / "classes"
    },
    packageWar <<= (assets, packageWar in Compile) map { (_, war: File) => war})

  lazy val webappDir = baseDirectory { _ / "src" / "main" / "webapp" }

 
  private def deps(d : ModuleID*) : Seq[Setting[_]] = Seq(libraryDependencies ++= d.toSeq)
 
  private def gameProject(name : String, d : ModuleID*) = Project(
    id = "games-" + name,
    base = file("games/" + name),
    settings = commonSettings ++ deps(d : _*)).
    dependsOn(game)
  
  object Dependencies {
	  lazy val jettyWebapp    = "org.eclipse.jetty" % "jetty-webapp" % jettyVersion % "container"
      lazy val jettyServlets  = "org.eclipse.jetty" % "jetty-servlets" % jettyVersion % "container"
      lazy val jettyJsp       = "org.eclipse.jetty" % "jetty-jsp-2.1" % jettyVersion % "container"
      lazy val jsp            = "org.mortbay.jetty" % "jsp-2.1-glassfish" % "2.1.v20100127" % "container"
      lazy val xstream        = "com.thoughtworks.xstream" % "xstream" % "1.4.2"
      lazy val log4j          = "log4j" % "log4j" % "1.2.16"
      lazy val velocity       = "org.apache.velocity" % "velocity" % "1.7"
      lazy val jstl           = "javax.servlet" % "jstl" % "1.2"
      lazy val guice          = "com.google.inject" % "guice" % "3.0-rc2"
      lazy val guiceBindings  = "com.google.inject.extensions" % "guice-multibindings" % "3.0-rc2"
      lazy val servletApi     = "javax.servlet" % "servlet-api" % "2.5" % "provided"
      lazy val jspApi         = "javax.servlet.jsp" % "jsp-api" % "2.1" % "provided"
      lazy val akkaActor      = "com.typesafe.akka" %% "akka-actor" % "2.1.4"
      lazy val slick          = "com.typesafe.slick" %% "slick" % "1.0.0"
      lazy val scalaReflect   = "org.scala-lang" % "scala-reflect" % "2.10.0"
      lazy val scalaCompiler  = "org.scala-lang" % "scala-compiler" % "2.10.0"
      lazy val sqlite         = "org.xerial" % "sqlite-jdbc" % "3.7.2"
      lazy val aws            = "com.amazonaws" % "aws-java-sdk" % "1.3.20"
      lazy val actuarius      = "eu.henkelmann" % "actuarius_2.10.0" % "0.2.5"
      lazy val specs2         = "org.specs2" %% "specs2" % "1.14"
      lazy val selenium       = "org.seleniumhq.selenium" % "selenium-firefox-driver" % "2.32.0" % "test"
      lazy val mockito        = "org.mockito" % "mockito-core" % "1.9.0" % "test"
      lazy val junit          = "junit" % "junit" % "4.10" % "test"
      lazy val akkaTestkit    = "com.typesafe.akka" %% "akka-testkit" % "2.1.1" % "test"
      lazy val sitemesh       = "opensymphony" % "sitemesh" % "2.4.2"
      lazy val junitInterface = "com.novocode" % "junit-interface" % "0.8" % "test"
      lazy val scalaTags      = "com.scalatags" % "scalatags_2.10" % "0.2.0"
      
      lazy val vraptor        = "br.com.caelum" % "vraptor" % "3.4.1" excludeAll (ExclusionRule(organization = "org.springframework"))
      
      lazy val jettyVersion   = "7.4.5.v20110725"
  }
  
  object Assets {
   
	lazy val tasks: Seq[Setting[_]] = Seq(gzipCss, gzipJs, gzipAssets)

	lazy val assets = TaskKey[Unit]("gzip-assets", "Resolve GZip to CSS and JS")

	private lazy val webappDirFile = file(".") / "web" / "src" / "main" / "webapp"

    private lazy val css = TaskKey[Unit]("gzip-css", "Resolve GZip to CSS")

    private lazy val js = TaskKey[Unit]("gzip-js", "Resolve GZip to JS")

    private lazy val gzipAssets = assets <<= (css, js) map { (_, _) => }

    private lazy val gzipCss = css :=  {
      gzipAsset(webappDirFile / "css", "css")
    }

    private lazy val gzipJs = js :=  {
      gzipAsset(webappDirFile / "js", "js")
    }

    private def gzipAsset(assetDir: File, assetType: String) = {
      val assets = listFiles(assetDir, FileFilter.globFilter("*." + assetType))
      val gzipOut = new GZIPOutputStream(new FileOutputStream(assetDir / "games-packaged." + assetType + ".gz"))
      val gzipPrinter = new PrintWriter(gzipOut)
      assets foreach(asset => gzipPrinter.print(new Scanner(asset).useDelimiter("$$").next))
      gzipPrinter.close()
      gzipOut.close()
    }
  }
}
