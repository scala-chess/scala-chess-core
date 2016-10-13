name := "scala-chess"

version := "1.0"

scalaVersion := "2.11.8"


libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "3.8.5" % "test")

scalacOptions in Test ++= Seq("-Yrangepos")