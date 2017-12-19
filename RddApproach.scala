package com.sample.spark

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object RddApproach extends App {
  val conf = new SparkConf()
    .setAppName("RDD approach")
    .setMaster("local")

  val sc = new SparkContext(conf)

  val input = args(0)

  // create rdd from text file
  val rdd = sc.textFile(input, 1)

  // convert rdd[string] to rdd[double]
  val drdd = rdd.map(x => x.toDouble)

  // find the sum
  val sum = drdd.sum

  // find the count
  val count = drdd.count

  // find the average
  val avg = sum / count

  println("Average: " + avg)
}


