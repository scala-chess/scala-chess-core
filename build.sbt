name := "scala-chess"

version := "1.0"

//lazy val scalaChessApi = RootProject(uri("git://github.com/markusheilig/scala-chess-api.git#master"))
lazy val scalaChessApi = RootProject(file("../scala-chess-api"))

lazy val `scala_chess_play` = (project in file(".")).dependsOn(scalaChessApi)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2-core" % "3.8.5" % "test",
  "org.scala-lang.modules" % "scala-xml_2.11" % "1.0.4",
  "com.typesafe.akka" % "akka-actor_2.11" % "2.4.12",
  "com.typesafe.akka" % "akka-remote_2.11" % "2.4.12"
)

scalacOptions in Test ++= Seq("-Yrangepos")