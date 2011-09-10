import sbt._

trait Defaults extends AndroidProject {
  import Process._

  def androidPlatformName = "android-13"
  def managedJavaPath = "src_managed" / "main" / "java"
  override def mainSourceRoots = super.mainSourceRoots +++ (managedJavaPath##)
  override def cleanAction = super.cleanAction dependsOn cleanTask(managedJavaPath)

  override def aaptGenerateTask = execTask {<x>
    {aaptPath.absolutePath} package -m
      -M {androidManifestPath.absolutePath}
      -S {mainResPath.absolutePath}
      -I {androidJarPath.absolutePath}
      -J {managedJavaPath.absolutePath}
  </x>} dependsOn(directory(mainJavaSourcePath), directory(managedJavaPath))
}

class Parent(info: ProjectInfo) extends ParentProject(info) {
  override def shouldCheckOutputDirectories = false
  override def updateAction = task { None }

  lazy val helloAndroid  = project("hello-android", "Hello, Android!", new MainProject(_))
  lazy val converter  = project("converter", "Converter", new MainProject(_))

  class MainProject(info: ProjectInfo) extends AndroidProject(info) with Defaults
}
