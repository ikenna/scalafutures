
name := "FutureNotes"

version := "1.0"

scalaVersion := "2.10.0"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.10" % "1.9.1" % "test",
  "ch.qos.logback" % "logback-classic" % "1.0.3",
  "ch.qos.logback" % "logback-core" % "1.0.3",
   "org.scalamock" %% "scalamock-scalatest-support" % "3.0.1" % "test"
)
