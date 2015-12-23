name := "Movie Recommendation"
version := "1.0"
scalaVersion := "2.11.7"
libraryDependencies ++= Seq(
	"org.apache.spark" %% "spark-core" % "1.5.2",
	"org.apache.spark" % "spark-mllib_2.11" % "1.5.2"
)
