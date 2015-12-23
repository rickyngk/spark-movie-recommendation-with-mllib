build:
	sbt package
run:
	~/spark-1.5.2/bin/spark-submit --class "Main" --master local[4] target/scala-2.11/movie-recommendation_2.11-1.0.jar

clean:
	sbt clean
