package com.sample.spark

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql._

object DataSetApproach extends App {
  val conf = new SparkConf()
    .setAppName("DataSet approach")
    .setMaster("local")

  val sc = new SparkContext(conf)

  val sqlContext: SQLContext = new SQLContext(sc)

  import sqlContext.implicits._

  val input = args(0)

  // create datafrom text file
  val df = sqlContext.read.text(input)

  // covert dataframe into dataset
  val ds = df.as[String]

  // convert dataframe[string] to dataframe[double]
  val dds = ds.map(x => x.toDouble)

  // find the sum of all the records
  val sum = dds.reduce(_ + _)

  // find the count of all the records
  val count = dds.count()

  // find average
  val avg = sum / count

  println("Average: " + avg)
}


