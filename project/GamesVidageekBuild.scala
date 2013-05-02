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

object GamesVidageekBuild extends Build {

  lazy val root = Project(
    "VidaGeekGames",
    file(".")
  ) settings(coreSettings ++ tasks ++ coreWebSettings: _*)

  lazy val commonSettings: Seq[Setting[_]] = Seq(
    organization := "net.vidageek",
    name := "games",
    version := "0.1-SNAPSHOT",
    scalaVersion := "2.10.0",
    scalacOptions ++= Seq("-encoding", "UTF-8", "-deprecation", "-unchecked", "-g:vars", "-feature", 
        "-language:_")
  )

  lazy val coreWebSettings = webSettings ++ inConfig(Runtime)(webappSettings0) ++ Seq(
    libraryDependencies ++= Seq(
      "org.eclipse.jetty" % "jetty-webapp" % "7.4.5.v20110725" % "container",
      "org.eclipse.jetty" % "jetty-servlets" % "7.4.5.v20110725" % "container",
      "org.eclipse.jetty" % "jetty-jsp-2.1" % "7.4.5.v20110725" % "container",
      "org.mortbay.jetty" % "jsp-2.1-glassfish" % "2.1.v20100127" % "container"
    )
  )

  lazy val coreSettings: Seq[Setting[_]] = commonSettings ++ Seq(
    libraryDependencies ++= Seq(
      "com.thoughtworks.xstream" % "xstream" % "1.4.2",
      "log4j" % "log4j" % "1.2.16",
      "org.apache.velocity" % "velocity" % "1.7",
      "javax.servlet" % "jstl" % "1.2",
      "com.google.inject" % "guice" % "3.0-rc2",
      "com.google.inject.extensions" % "guice-multibindings" % "3.0-rc2",
      "javax.servlet" % "servlet-api" % "2.5" % "provided",
      "javax.servlet.jsp" % "jsp-api" % "2.1" % "provided",
      "br.com.caelum" % "vraptor" % "3.4.1" excludeAll (
        ExclusionRule(organization = "org.springframework")
      ),
      "com.typesafe.akka" %% "akka-actor" % "2.1.1",
      "com.typesafe.slick" %% "slick" % "1.0.0",
      "org.scala-lang" % "scala-reflect" % "2.10.0",
      "org.scala-lang" % "scala-compiler" % "2.10.0",
      "org.xerial" % "sqlite-jdbc" % "3.7.2",
      "com.amazonaws" % "aws-java-sdk" % "1.3.20",
      "eu.henkelmann" % "actuarius_2.10.0" % "0.2.5",
      "org.specs2" %% "specs2" % "1.14",
      "org.mockito" % "mockito-core" % "1.9.0" % "test",
      "junit" % "junit" % "4.10" % "test",
      "com.typesafe.akka" %% "akka-testkit" % "2.1.1" % "test"
    ).map{ dep =>
      if (List("log4j", "javax.servlet").contains(dep.organization)) dep
      else dep.withSources
    } ++ Seq(
      "opensymphony" % "sitemesh" % "2.4.2",
      "com.novocode" % "junit-interface" % "0.8" % "test"
    ),
    classDirectory in Compile <<= webappDir {
      _ / "WEB-INF" / "classes"
    }
  )

  lazy val webappDir = baseDirectory {
    _ / "src" / "main" / "webapp"
  }

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
  
}
