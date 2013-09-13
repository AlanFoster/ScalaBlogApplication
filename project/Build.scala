import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "BlogApplication"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    "com.typesafe.play" %% "play-slick" % "0.4.0",

	  "com.jolbox" % "bonecp" % "0.7.1.RELEASE",
	  "mysql" % "mysql-connector-java" % "5.1.21",
    "org.slf4j" % "slf4j-nop" % "1.6.4",
    "com.h2database" % "h2" % "1.3.166",
    "org.xerial" % "sqlite-jdbc" % "3.7.2"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
      resolvers += "Sonatype OSS Releases" at "http://oss.sonatype.org/content/repositories/releases/",
      resolvers += "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"
  ).dependsOn(RootProject( uri("git://github.com/freekh/play-slick.git") ))

}
