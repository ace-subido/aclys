import sbtassembly.Plugin.AssemblyKeys._

assemblySettings

organization := "com.aclys"

name := "aclys"

version := "1.0.0.0"

scalaVersion := "2.10.0"

libraryDependencies ++= Seq(
  "com.twitter" %% "finagle-core" % "6.5.0",
  "com.twitter" %% "finagle-http" % "6.5.0",
  "org.json4s" %% "json4s-native" % "3.2.4",  
  "commons-io" % "commons-io" % "2.4",
  "commons-codec" % "commons-codec" % "1.7",
  "junit" % "junit" % "4.11" % "test",
  "org.mockito" % "mockito-all" % "1.9.5" % "test",
  "org.scalatest" %% "scalatest" % "1.9.1" % "test"
)

jarName in assembly := "aclys.jar"

test in assembly := {}