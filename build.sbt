lazy val gve = (project in file(".")).
  enablePlugins(ScalaJSPlugin).
//  enablePlugins(ScalaJSBundlerPlugin).
  settings(
    name := "gve",
    organization := "com.github.skac112",
    version := "0.1.1-SNAPSHOT",
    scalaVersion := "3.3.0",
    libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "2.8.0",
    libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.15.4" % "test",
    libraryDependencies += "com.lihaoyi" %%% "upickle" % "3.1.3",
    libraryDependencies += "com.github.skac112" %%% "euler" % "2.0.0-SNAPSHOT",
    scalaJSUseMainModuleInitializer := true,
    scalacOptions += "-Ypartial-unification"
  )

