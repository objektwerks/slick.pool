name := "slick.pool"
organization := "objektwerks"
version := "0.2-SNAPSHOT"
scalaVersion := "3.5.1-RC2"
libraryDependencies ++= {
  val slickVersion = "3.5.1"
  Seq(
    "com.typesafe.slick" %% "slick" % slickVersion,
    "com.typesafe.slick" %% "slick-hikaricp" % slickVersion,
    "com.h2database" % "h2" % "2.3.232",
    "ch.qos.logback" % "logback-classic" % "1.5.8",
    "org.scalatest" %% "scalatest" % "3.2.19" % Test
  )
}
scalacOptions ++= Seq(
  "-Wunused:all",
  "-unchecked", "-deprecation"
)
