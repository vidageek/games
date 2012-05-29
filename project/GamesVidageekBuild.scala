import sbt._
import Keys._
import com.github.siasia._
import WebPlugin._
import WebappPlugin._
import IO._
import PluginKeys._

object GamesVidageekBuild extends Build {

  lazy val root = Project(
    "VidaGeek games",
    file(".")
  ) settings(coreSettings ++ tasks ++ coreWebSettings: _*)

  lazy val commonSettings: Seq[Setting[_]] = Seq(
    organization := "net.vidageek",
    name := "games",
    version := "0.1-SNAPSHOT",
    scalaVersion := "2.9.2",
    scalacOptions ++= Seq("-encoding", "UTF-8", "-deprecation", "-unchecked", "-g:vars")
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
	    "javax.servlet" % "servlet-api" % "2.5" % "provided",
	    "br.com.caelum" % "vraptor" % "3.4.1" excludeAll (
	      ExclusionRule(organization = "org.springframework")
	    ),
	    "org.mockito" % "mockito-core" % "1.9.0" % "test",
	    "junit" % "junit" % "4.10" % "test",
	    "com.novocode" % "junit-interface" % "0.8" % "test",
	    "org.specs2" % "specs2_2.9.1" % "1.8.1" % "test"
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

    val finalAsset = assetDir / ("games-packaged." + assetType)

    assets foreach(asset => append(finalAsset, readBytes(asset)))
  }
}
