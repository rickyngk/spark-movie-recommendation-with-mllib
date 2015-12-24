import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.SparkContext._
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.recommendation.{ALS, Rating, MatrixFactorizationModel}

object Main {
    var sc: SparkContext = null;
    def main(args: Array[String]) {
        val conf = new SparkConf().setAppName("Movie Recommendation")
		sc = new SparkContext(conf)
        loadRatings("/Users/duynk/projects/movie-recommendation/dataset/ml-1m/ratings.dat")
        loadMovies("/Users/duynk/projects/movie-recommendation/dataset/ml-1m/movies.dat")
    }

    def loadRatings(path: String) {
        val ratings = sc.textFile(path).map { line =>
            var fields = line.split("::")
            (fields(3).toLong % 10, Rating(fields(0).toInt, fields(1).toInt, fields(2).toDouble))
        }
        println(sc.textFile(path).count)
    }

    def loadMovies(path: String) {
        val ratings = sc.textFile(path).map { line =>
            var fields = line.split("::")
            (fields(0).toInt, fields(1))
        }.collect().toMap
        println(sc.textFile(path).count)
    }
}
