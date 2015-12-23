import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.SparkContext._
import org.apache.spark.rdd.RDD

object Main {
    def main(args: Array[String]) {
        val conf = new SparkConf().setAppName("Movie Recommendation")
		val sc = new SparkContext(conf);

        println("Hello");
    }
}
