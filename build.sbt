import sbtassembly.Plugin.AssemblyKeys._

assemblySettings

organization := "com.orchestronix"

name := "finagle-http-quickstart"

version := "1.0.0.0-SNAPSHOT"

scalaVersion := "2.9.2"

libraryDependencies ++= Seq(
  "com.twitter" %% "finagle-core" % "6.3.0",
  "com.twitter" %% "finagle-http" % "6.3.0",
  "org.json4s" %% "json4s-native" % "3.2.4",
  "joda-time" % "joda-time" % "2.2",
  "com.google.inject" % "guice" % "3.0",
  "net.codingwell" %% "scala-guice" % "3.0.2",
  "org.joda" % "joda-convert" % "1.3.1",
  "org.slf4j" % "slf4j-api" % "1.7.5",
  "ch.qos.logback" % "logback-classic" % "1.0.11",
  "ch.qos.logback" % "logback-core" % "1.0.11",
  "commons-io" % "commons-io" % "2.4",
  "commons-codec" % "commons-codec" % "1.7",
  "org.fusesource.jansi" % "jansi" % "1.9",
  "junit" % "junit" % "4.11" % "test",
  "org.mockito" % "mockito-all" % "1.9.5" % "test",
  "org.scalatest" %% "scalatest" % "1.9.1" % "test"
)

jarName in assembly := "finagle-http-quickstart.jar"

test in assembly := {}