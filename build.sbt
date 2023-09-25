ThisBuild / name := "solr.almaren"
ThisBuild / organization := "com.github.music-of-the-ainur"

lazy val scala212 = "2.12.10"

ThisBuild / scalaVersion := scala212

val sparkVersion = "3.4.1"
val majorVersionReg = "([0-9]+\\.[0-9]+).{0,}".r

val majorVersionReg(majorVersion) = sparkVersion

scalacOptions ++= Seq("-deprecation", "-feature")

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
  "com.github.music-of-the-ainur" %% "almaren-framework" % s"0.9.10-${majorVersion}" % "provided",
  "com.lucidworks.spark" % "spark-solr" % "4.0.4" % "provided",
  // Mising library from spark-solr connector
  "commons-httpclient" % "commons-httpclient" % "3.1",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5" % "test",
  "org.scalatest" %% "scalatest" % "3.2.14" % "test",
  "com.lucidworks.spark" % "spark-solr" % "4.0.4" % "test",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.12.2"
)

ThisBuild / useCoursier := false

enablePlugins(GitVersioning)

resolvers += "Central Maven repository" at "https://repo.maven.apache.org/maven2"

resolvers +=
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/service/local/repositories/releases/content"

ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/music-of-the-ainur/solr.almaren"),
    "scm:git@github.com:music-of-the-ainur/solr.almaren.git"
  )
)
ThisBuild / developers := List(
  Developer(
    id    = "mantovani",
    name  = "Daniel Mantovani",
    email = "daniel.mantovani@modak.com",
    url   = url("https://github.com/music-of-the-ainur")
  ),
  Developer(
    id = "badrinathpatchikolla",
    name = "Badrinath Patchikolla",
    email = "badrinath.patchikolla@modakanalytics.com",
    url = url("https://github.com/music-of-the-ainur")
  )
)

ThisBuild / description := "Solr Connector For Almaren Framework"
ThisBuild / licenses := List("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt"))
ThisBuild / homepage := Some(url("https://github.com/music-of-the-ainur/solr.almaren"))
ThisBuild / organizationName := "Music of Ainur"
ThisBuild / organizationHomepage := Some(url("https://github.com/music-of-the-ainur"))


// Remove all additional repository other than Maven Central from POM
credentials += Credentials(Path.userHome / ".sbt" / "sonatype_credential")
ThisBuild / pomIncludeRepository := { _ => false }
ThisBuild / publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

ThisBuild / publishMavenStyle := true
updateOptions := updateOptions.value.withGigahorse(false)
