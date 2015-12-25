import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.SparkContext._
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.recommendation.{ALS, Rating, MatrixFactorizationModel}
import scala.io.Source
import org.apache.log4j.{Logger, Level}

object Main {
    var sc: SparkContext = null;
    def main(args: Array[String]) {
        Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
        Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)

        val conf = new SparkConf().setAppName("Movie Recommendation")
		sc = new SparkContext(conf)
        val ratings = loadRatings("/Users/duynk/projects/movie-recommendation/dataset/ml-1m/ratings.dat")
        val movies = loadMovies("/Users/duynk/projects/movie-recommendation/dataset/ml-1m/movies.dat")
        val personalRatings = loadPersonalRatings("/Users/duynk/projects/movie-recommendation/personalRatings.txt")
    }

    def loadRatings(path: String): RDD[(Long, Rating)] = {
        val ratings = sc.textFile(path).map { line =>
            var fields = line.split("::")
            (fields(3).toLong % 10, Rating(fields(0).toInt, fields(1).toInt, fields(2).toDouble))
        }
        ratings
    }

    def loadMovies(path: String): Map[Int, String] = {
        val movies = sc.textFile(path).map { line =>
            var fields = line.split("::")
            (fields(0).toInt, fields(1))
        }.collect().toMap
        movies
    }

    def loadPersonalRatings(path: String): Seq[Rating] = {
        var ratings = sc.textFile(path).map { line =>
            var fields = line.split("::")
            Rating(fields(0).toInt, fields(1).toInt, fields(2).toDouble)
        }
        ratings.collect().toSeq
    }
}
