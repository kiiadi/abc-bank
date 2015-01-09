name := "bank"

version := "1.0-SNAPSHOT"
scalaVersion := "2.11.4"

javacOptions in compile ++= List("-target", "1.8", "-source", "1.8")

libraryDependencies ++= Seq(
   	"junit" % "junit" % "4.11" % "test",

	"org.scalatest" %% "scalatest" % "2.2.1" % "test",
	"org.scalautils" %% "scalautils" % "2.1.5",
   	"com.novocode" % "junit-interface" % "0.11" % "test",
   	"org.scala-sbt" % "test-interface" % "1.0"
)
