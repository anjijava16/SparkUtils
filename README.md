
# Step1 :) spark-shell --master yarn --conf spark.ui.port=12345 

# Step 2:)spark-shell --master yarn --executor-memory 4g --driver-memory 4g --num-executors 10 --conf spark.shuffle.consolidateFiles=true -i abc.scala  --conf spark.driver.args="$1 $2 $3" > log.txt 2>1&1
---------------------------------

Step 3:) spark-shell -i Welcome.scala
=============================================================
val x=100;
val y=200;
def mytest():Unit={
println(x+"   "+y);
}

def mytest2():Unit={
mytest();
println("Sum of 2 "+(x+y));
}

mytest2();
System.exit(0)
=============================================================


Step 4:) spark-shell -i WordCountscala.scala

WordCountscala.scala
=============================================================
println("Stared With Main Example ");
val text = sc.textFile("/user/anjaiahspr/sparksubmit/mytextfile.txt") 
 val counts = text.flatMap(line => line.split(" ")
 ).map(word => (word,1)).reduceByKey(_+_) counts.collect

println("Ended With Main Example ");
System.exit(0)
=============================================================



# SparkUtils

URL : 6 Nodes
16 core each
64 GB RAM each

URL : https://www.youtube.com/watch?v=7ooZ4S7Ay6Y&t=1211s


https://www.youtube.com/watch?v=7ooZ4S7Ay6Y&t=1211s

https://spark-summit.org/wp-content/uploads/2015/03/SparkSummitEast2015-AdvDevOps-StudentSlides.pdf

==================================================================================================

Types of RDD'S

https://github.com/apache/spark/blob/master/core/src/main/scala/org/apache/spark/rdd/RDD.scala


RDD (Abstract class)
i)HadoopRDD
ii)FilterRDD
iii)MappedRDD
iv)PairRDD
v)ShuffledRDD
vi)UnionRDD
vii)PythonRDD
viii)DoubleRDD
ix)JDBCRDD
x)SchemaRDD
xi)JsonRDD
xii)VectorRDD
xiii)EdgeRDD

==================================================================================================


Trasformations (LAZY)
i)map
ii)flatMap()
iii)filter()
iv)mapPartitions()
v)mapPartitionWithIndex()
vi)sample()
vii)union()
viii)inersection()
ix)distinct
x)groupByKey()
xi)reduceByKey()
xii)sortByKey()
xiii)join
xiv)cogroup
xv)cartesion()
xvi)pipe()
xvii)coalesce()
xviii)repartition()
xix)paritionBy()

=======================================================================

Actions

i)reduce()
ii)collect()
iii)count()
iv)first()
v)take()
vi)takeSample()
vii)saveToCassandra()
viii)takeOrdered()
ix)saveAsTextFile()
x)saveAsSequenceFile()
xi)saveAsObjectFile()
xii)countByKey()
foreach()




