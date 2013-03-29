
scalaVersion := "2.9.2"

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.1.1")

resolvers += "remeniuk repo" at "http://remeniuk.github.com/maven"

libraryDependencies <+= sbtVersion {  
	case x if (x.startsWith("0.12")) => "com.github.siasia" %% "xsbt-web-plugin" % "0.12.0-0.2.11.1"
}

