name := "core"

version := "0.6"

organization := "org.bowlerframework"

scalaVersion := "2.9.1"

//artifact-name := "bingo"

//seq(webSettings: _*)

//seq(net.virtualvoid.sbt.graph.Plugin.graphSettings: _*)

resolvers ++= Seq(
    "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/",
    "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases",
    "Novus Salat Snapshots" at "http://repo.novus.com/snapshots",
	"Scala-Tools repo" at "http://scala-tools.org/repo-releases/",
	"Spring Repo" at "http://maven.springframework.org/milestone",
	"Buzz Media" at "http://maven.thebuzzmedia.com",
	"Codahale" at "http://repo.codahale.com"
    )

resolvers += "sbt-idea-repo" at "http://mpeltonen.github.com/maven/"


libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % "2.0.2",
  "org.scalatra" %% "scalatra-scalate" % "2.0.2",
  "org.scalatra" %% "scalatra-specs2" % "2.0.2" % "test",
  "org.slf4j" % "slf4j-api" % "1.6.4",
  "net.liftweb" % "lift-common_2.9.1" % "2.4",
  "net.liftweb" % "lift-json_2.9.1" % "2.4",
  "net.liftweb" % "lift-json-ext_2.9.1" % "2.4",
  "com.recursivity" %% "recursivity-commons" % "0.6",
  "org.scalatra" % "scalatra-fileupload_2.9.1" % "2.0.4",
  "org.scalatra" % "scalatra-scalate_2.9.1" % "2.0.4" % "runtime",
  "javax.servlet" % "javax.servlet-api" % "3.0.1" % "provided",
  "com.mongodb.casbah" %% "casbah" % "2.1.5-1",
  "com.novus" %% "salat-core" % "0.0.8-SNAPSHOT",
  "org.scalatra" %% "scalatra" % "2.0.4",
  "org.scalatra" %% "scalatra-scalate" % "2.0.4",
  "org.scalatra" %% "scalatra-specs2" % "2.0.4" % "test",
  //"org.bowlerframework" %% "core" % "0.5.1" exclude("org.slf4j", "slf4j-nop"),
  "commons-fileupload" % "commons-fileupload" % "1.2.2",
  "org.scalatra" %% "scalatra-scalatest" % "2.0.2" % "test"
  )

// exclude("javax.servlet") --for commons fileupload

// append several options to the list of options passed to the Java compiler
//javacOptions ++= Seq("-source", "1.6", "-target", "1.6")

// append -deprecation to the options passed to the Scala compiler
scalacOptions += "-deprecation"

//webappClasspath := super.webappClasspath +++ buildCompilerJar

unmanagedClasspath in Runtime <+= (baseDirectory) map { bd => Attributed.blank(bd / "config") }

