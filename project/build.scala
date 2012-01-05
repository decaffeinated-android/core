import sbt._

import Keys._
import AndroidKeys._

object Settings {
  val default = Defaults.defaultSettings ++ Seq (
    organization := "com.decaffeinatedandroid.core",
    version := "1.0",
    scalaVersion := "2.9.1"
  )

  lazy val android = Settings.default ++ AndroidProject.androidSettings ++ Seq (
    platformName in Android := "android-8"
  )
}

object AndroidBuild extends Build {
  lazy val helloAndroid = Project (
    "hello-android",
    file("hello-android"),
    settings = Settings.android
  )

  lazy val converter = Project (
    "converter",
    file("converter"),
    settings = Settings.android
  )
}
