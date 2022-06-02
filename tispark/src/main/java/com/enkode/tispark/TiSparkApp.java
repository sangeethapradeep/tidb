package com.enkode.tispark;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;

public class TiSparkApp {

  public static void main(String[] args) {
    SparkConf conf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("TiSparkJavaEg")
      .set("spark.sql.extensions", "org.apache.spark.sql.TiExtensions")
      .set("spark.tispark.pd.addresses", "127.0.0.1:56909");

    SparkSession spark = SparkSession.builder().config(conf).getOrCreate();

    spark.sql("show databases").show();
    spark.sql("use test");
    spark.sql("select count(*) from employee").show();
  }

}
