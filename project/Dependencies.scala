/**
  * The definition of *ALL Dependencies*
  */
import sbt._, Keys._
import bintray.BintrayKeys._

object Dependencies {
  case class Dpd(groupId: String,
                 artifactId: String,
                 revision: String,
                 autoScalaVersion: Boolean = true,
                 configuration: String = "compile") {
    def libraryDependencies =
      if (autoScalaVersion) groupId %% artifactId % revision % configuration
      else groupId                  % artifactId  % revision % configuration
  }

  // all dependencies
  object all {
    object versions {
      val scalatest = "3.0.5"
      val scopt     = "3.7.0"
    }
    lazy val scalatest = Seq(
      Dpd("org.scalactic", "scalactic", versions.scalatest),
      Dpd("org.scalatest", "scalatest", versions.scalatest, autoScalaVersion = true, configuration = "test")
    ).map(_.libraryDependencies)

    lazy val scopt = Seq(Dpd("com.github.scopt", "scopt", versions.scopt)).map(_.libraryDependencies)
  }

  // resolvers
  object resolver {
    lazy val local = "local maven repository" at "file://" + Path.userHome.absolutePath + "/.m2/repository"
    object credential {
      lazy val jfrog  = Credentials(Path.userHome / ".dev" / "jfrog.credentials")
    }
  }
}