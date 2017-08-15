package com.madhukaraphatak.spark.rdd.anatomy

import org.apache.spark.{Partitioner, SparkContext}
import org.apache.spark.SparkContext._

/**
 * Created by madhu on 11/3/15.
 */
object CustomPartitionExample {


  
  def main(args: Array[String]) {
    
    val sc = new SparkContext("local[*]","custom partitioning example")

/*    val salesData = sc.textFile(args(1))
*/

     val salesData = sc.textFile("/home/hadoop/sai_Spark_oozie/sparkUtils/src/main/resources/sales.csv")

    val salesByCustomer = salesData.map(value => {
      val colValues = value.split(",")
      (colValues(1),colValues(3).toDouble)
    })

    val groupedData = salesByCustomer.groupByKey(new CustomerPartitioner)

    //printing partition specific data

    val groupedDataWithPartitionData = groupedData.mapPartitionsWithIndex{
      case(partitionNo,iterator) => {
       List((partitionNo,iterator.toList.length)).iterator
      }
    }

    println(groupedDataWithPartitionData.collect().toList)
    
    
    
  }
  

}
