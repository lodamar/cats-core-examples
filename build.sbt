// https://typelevel.org/sbt-typelevel/faq.html#what-is-a-base-version-anyway
ThisBuild / tlBaseVersion := "0.0" // your current series x.y

ThisBuild / organization := "it.bitrock"
ThisBuild / organizationName := "Bitrock"
ThisBuild / startYear := Some(2022)
ThisBuild / licenses := Seq(License.Apache2)
ThisBuild / developers := List(
  // your GitHub handle and name
  tlGitHubDev("lodamar", "Daniele Bonelli")
)

val Scala213 = "2.13.8"
ThisBuild / scalaVersion := Scala213 // the default Scala

lazy val root = (project in file(".")).aggregate(core)

lazy val core = project
  .in(file("core"))
  .settings(
    name := "scala-for-dummies",
    libraryDependencies ++= Seq(
      "org.typelevel" %%% "cats-core" % "2.8.0",
      "org.typelevel" %%% "cats-effect" % "3.3.14",
      "org.scalameta" %%% "munit" % "0.7.29" % Test,
      "org.typelevel" %%% "munit-cats-effect-3" % "1.0.7" % Test
    )
  )
