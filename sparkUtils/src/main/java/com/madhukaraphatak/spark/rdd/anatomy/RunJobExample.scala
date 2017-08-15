package com.madhukaraphatak.spark.rdd.anatomy

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

/**
 * Created by madhu on 24/3/15.
 */
object RunJobExample {

  def main(args: Array[String]) {

    val sc = new SparkContext(args(0), "run job example")
    val salesData = sc.textFile(args(1))

    println("built in collect " +salesData.collect().toList)
    //implement collect using runJob API

    val results = sc.runJob(salesData, (iter: Iterator[String]) => iter.toArray)
    val collectedResult = Array.concat(results: _*).toList
    println("result of custom collect " +collectedResult)
    
    
    /*val conf = new SparkConf().setAppName("App name").setMaster("yarn")
  val ssc = new StreamingContext(conf, Seconds(1))
    */
    
    


  }

}
