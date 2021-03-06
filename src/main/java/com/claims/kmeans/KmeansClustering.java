package com.claims.kmeans;


import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.mllib.clustering.KMeans;
import org.apache.spark.mllib.clustering.KMeansModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import scala.Tuple2;

import java.util.Map;
import java.util.regex.Pattern;

public class KmeansClustering {

    static int i=0;

    private static class ParsePoint implements Function<String, Vector> {
        private static final Pattern SPACE = Pattern.compile(",");

        public Vector call(String line) {
            String[] tok = SPACE.split(line);
            double[] point = new double[tok.length - 1];
            for (int i = 1; i < tok.length; ++i) {
                try {
                        point[i - 1] = Double.parseDouble(tok[i]);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            return Vectors.dense(point);
        }
    }

    private static class ParseTitle implements Function<String, String> {
        private static final Pattern SPACE = Pattern.compile(",");

        public String call(String line) {
            String[] tok = SPACE.split(line);
            return tok[0];
        }
    }

    private static class ClusterCars implements PairFunction<Tuple2<String, Vector>, Integer, String> {
        private KMeansModel model;

        ClusterCars(KMeansModel model) {
            this.model = model;
        }

        public Tuple2<Integer, String> call(Tuple2<String, Vector> args) {
            String title = args._1();
            Vector point = args._2();
            int cluster = model.predict(point);
            return new Tuple2<>(cluster, title);
        }
    }


    public static void main(String[] args) {
        String results_path = "/Users/karthiks/Google Drive/illinois/masters/CS411/project_assignment/runtime/src/main/resources/output";
        int k = 4;
        int iterations = 100;
        int runs = 1;
        long seed = 0;

        SparkConf sparkConf = new SparkConf().setAppName("KMeans MP").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(sparkConf);
        JavaRDD<String> lines = sc.textFile("/Users/karthiks/Google Drive/illinois/masters/CS411/project_assignment/runtime/src/main/resources/MemberDetails.csv",1);
        JavaRDD<Vector> points = lines.map(new ParsePoint());
        JavaRDD<String> titles = lines.map(new ParseTitle());


        KMeansModel model = KMeans.train(points.rdd(), k, iterations, runs, KMeans.RANDOM(), seed);
        JavaPairRDD<Integer, Iterable<String>> results = titles.zip(points).mapToPair(new ClusterCars(model)).groupByKey();
        Map<Integer, Iterable<String>> integerIterableMap = results.collectAsMap();

        sc.stop();
    }
}
