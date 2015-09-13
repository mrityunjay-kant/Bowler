name := "core"

version := "0.6"

organization := "org.bowlerframework"

scalaVersion := "2.10.4"

//artifact-name := "bingo"

resolvers ++= Seq(
    "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/",
    "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases",
    "Novus Salat Snapshots" at "http://repo.novus.com/snapshots",
	"Scala-Tools repo" at "http://scala-tools.org/repo-releases/",
	"Spring Repo" at "http://maven.springframework.org/milestone",
	"Buzz Media" at "http://maven.thebuzzmedia.com",
	"Codahale" at "http://repo.codahale.com"
    )

resolvers += "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"

libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % "2.3.1",
  "org.scalatra" %% "scalatra-scalate" % "2.3.1",
  "org.scalatra" %% "scalatra-specs2" % "2.3.1" % "test",
  "org.scalatra" %% "scalatra-fileupload" % "2.3.1",
  "org.scalatra" %% "scalatra-scalate" % "2.3.1" % "runtime",
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
  "org.slf4j" % "slf4j-api" % "1.6.4",
  "net.liftweb" %% "lift-json" % "2.6.2",
  "net.liftweb" %% "lift-json-ext" % "2.6.2",
  "com.recursivity" %% "recursivity-commons" % "0.6"
  )

// exclude("javax.servlet") --for commons fileupload

// append several options to the list of options passed to the Java compiler
//javacOptions ++= Seq("-source", "1.6", "-target", "1.6")

// append -deprecation to the options passed to the Scala compiler
scalacOptions += "-deprecation"

//webappClasspath := super.webappClasspath +++ buildCompilerJar

unmanagedClasspath in Runtime <+= (baseDirectory) map { bd => Attributed.blank(bd / "config") }

