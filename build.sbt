name := "Scrabble"
version := "0.2"
scalaVersion := "2.12.8"

// Scala Test's
libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"

// Google Guice 4 Dependency Injection
libraryDependencies += "com.google.inject" % "guice" % "4.1.0"
libraryDependencies += "net.codingwell" %% "scala-guice" % "4.1.0"

// Skala Swing 4 GUI
libraryDependencies += "org.scala-lang.modules" % "scala-swing_2.12" % "2.0.3"

// Json and XML 4 io Database Basics 
libraryDependencies += "org.scala-lang.modules" % "scala-xml_2.12" % "1.0.6"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.6"