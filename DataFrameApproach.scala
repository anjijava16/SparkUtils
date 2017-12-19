package com.sample.spark

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql._

object DataFrameApproach extends App {
  val conf = new SparkConf()
    .setAppName("DataFrame approach")
    .setMaster("local")

  val sc = new SparkContext(conf)

  val sqlContext: SQLContext = new SQLContext(sc)

  import sqlContext.implicits._

  val input = args(0)

  // create datafrom text file
  val df = sqlContext.read.text(input)

  // find the sum of all the records
  val sum = df.map(x => x.getAs[String](0).toDouble).sum

  // find the count of all the records
  val count = df.count()

  // find average
  val avg = sum / count

  println("Average: " + avg)
}


