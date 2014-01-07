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

  lazy val root = Project(
    id = "VidaGeekGames",
    base = file("."),
    settings = (coreSettings ++ tasks ++ coreWebSettings),
    aggregate = Seq(web))
    
  lazy val web = Project(
    id = "games",
    base = file("web"),
    settings = (coreSettings ++ tasks ++ coreWebSettings)
  )
  

  lazy val commonSettings: Seq[Setting[_]] = Defaults.defaultSettings ++ Seq(
    organization := "net.vidageek",
    version := "0.1-SNAPSHOT",
    scalaVersion := "2.10.0",
    scalacOptions ++= Seq("-encoding", "UTF-8", "-deprecation", "-unchecked", "-g:vars", "-feature", 
        "-language:_")
  )

  lazy val coreWebSettings: Seq[Setting[_]] = webSettings ++ inConfig(Runtime)(webappSettings0) ++ Seq(
    libraryDependencies ++= Seq(jettyWebapp, jettyServlets, jettyJsp, jsp),
    classDirectory in Compile <<= webappDir {
      _ / "WEB-INF" / "classes"
    })

  lazy val coreSettings: Seq[Setting[_]] = commonSettings ++ Seq(
    libraryDependencies ++= Seq(xstream, log4j, velocity, jstl, guice, guiceBindings, servletApi,
        jspApi, vraptor, akkaActor, slick, scalaReflect, scalaCompiler, sqlite, aws, actuarius, specs2,
        selenium, mockito, junit, akkaTestkit, sitemesh, junitInterface)
  )

  lazy val webappDir = baseDirectory { _ / "src" / "main" / "webapp" }

  lazy val webappDirFile = file(".") / "src" / "main" / "webapp"

  lazy val tasks: Seq[Setting[_]] = Seq(gzipCss, gzipJs, gzipAssets)

  lazy val assets = TaskKey[Unit]("gzip-assets", "Resolve GZip to CSS")

  lazy val css = TaskKey[Unit]("gzip-css", "Resolve GZip to CSS")

  lazy val js = TaskKey[Unit]("gzip-js", "Resolve GZip to JS")

  lazy val gzipAssets = assets <<= (css, js) map { (_, _) => }

  packageWar <<= (assets, packageWar) map { (_, war: File) => war}

  lazy val gzipCss = css :=  {
    gzipAsset(webappDirFile / "css", "css")
  }

  lazy val gzipJs = js :=  {
    gzipAsset(webappDirFile / "js", "js")
  }

  def gzipAsset(assetDir: File, assetType: String) = {
    val assets: Array[File] = listFiles(assetDir, FileFilter.globFilter("*." + assetType))
    val gzipOut: GZIPOutputStream = new GZIPOutputStream(new FileOutputStream(assetDir / "games-packaged." + assetType + ".gz"))
    val gzipPrinter = new PrintWriter(gzipOut)
    assets foreach(asset => gzipPrinter.print(new Scanner(asset).useDelimiter("$$").next))
    gzipPrinter.close()
    gzipOut.close()
  }  
  
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
      
      lazy val vraptor       = "br.com.caelum" % "vraptor" % "3.4.1" excludeAll (ExclusionRule(organization = "org.springframework"))
      
      
      lazy val jettyVersion = "7.4.5.v20110725"
  }
  
  
}
