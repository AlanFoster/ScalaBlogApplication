import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "BlogApplication"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    jdbc,
    anorm,
	  "com.jolbox" % "bonecp" % "0.7.1.RELEASE",
	  "mysql" % "mysql-connector-java" % "5.1.21",
	  "org.squeryl" %% "squeryl" % "0.9.5-6"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
      resolvers += "Sonatype OSS Releases" at "http://oss.sonatype.org/content/repositories/releases/",
      resolvers += "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"
  )

}
