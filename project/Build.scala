import sbt._
import Keys._

object TasksBuild extends Build {
  val css = TaskKey[Unit]("css", "Resolve GZip to CSS")

val gzipCss = css := {
  //FileUtilities.copyFile( webAppDir + "css/**/*.css",
   // webAppDir + "css/games-packaged.css.gz",
    //                         log)
    //log.info("Copied jetty-web.xml into place")
    None
} 

lazy val project = Project (
    "games",
    file ("."),
    settings = Defaults.defaultSettings ++ Seq(gzipCss)
  )
}
