export SPARK_MAJOR_VERSION=2
spark-shell --master yarn \
  --conf spark.ui.port=12245 \
  --num-executors 1 \
  --executor-memory 512M \
  --class wordCount.scala 10 10

======================================================================================================================================
val orders=sc.textFile("/public/retail_db/orders");
val orderMap=orders.map(order=>(order.split(",")(3),"")).countByKey.foreach(println)
orders.map(order=>(order.split(",")(3),"")).countByKey
orders.map(order=>(order.split(",")(3),"")).countByKey.foreach(println)
orders.map(order=>(order.split(",")(2),"")).countByKey.foreach(println)


Note: foreach work only data strucures array,list,map won't work for RDD.

reduce can't perfom  by key 


val orderItems=sc.textFile("/public/retail_db/order_items")
val orderItemRevenue=orderItems.map(orderItem=>orderItem.split(",")(4).toFloat)
orderItemRevenue.reduce((total,revenue)=>total+revenue)
orderItemRevenue.reduce((total ,revenue)=>{
 if(total<revenue) revenue else total
})
(OR)
val orderItemsMaxRevenue = orderItemsRevenue.reduce((max, revenue) => {
  if(max < revenue) revenue else max
})

groupByKey doesn't use combine

reudceBy And aggregate ==Use Combiner

groupByKey                     reduceByKey and aggregraeByKey
Not Use Combiner                 Using the Combiner
Ex: 1 to 100
sum(1 to 100)==>
    =1+2+...100                    sum(1 to 100)=sum(sum(1 to 10),sum(11,50),sum(51,100))

#======================================================================================================================================

val orderItems=sc.textFile("/public/retail_db/order_items")
val orderItemsMap=orderItems.map(oi=>(oi.split(",")(1).toInt,oi.split(",")(4).toFloat))

val orderGrpKey=orderItemsMap.groupByKey
orderGrpKey.take(10)
orderGrpKey.map(rec=>rec._2.toList.sum).take(10).foreach(println)

orderGrpKey.map(rec=>(rec._1,rec._2.toList.sum)).take(10).foreach(println)

orderGrpKey.flatMap(
rec=>{
	rec._2.toList.sortBy(o=>-0).map)(k=>(rec._1,k))
})
l.sortBy(o=>-o)
CompaceBuffer (Spark Collection) : In Memoery represent collection

val l=Iterable(119.8,400.0,10,20)
val l=Iterable(119.8,400.0,10,20).toList

==========================================================
reduceByKey(func,)
input & output must be pair RDD

// Aggregation -reduceBy key

val orderItems=sc.textFile("/public/retail_db/order_items")
val orderItemsMap=orderItems.map(oi=>(oi.split(",")(1).toInt,oi.split(",")(4).toFloat))

val revenuePerOrderId=orderItemsMap.reduceByKey((total,revenue)=>total+revenue)

val minRevenuePerOrderId=orderItemsMap.reduceByKey((min,revenue)=> if(min>revenue) revenue else min)

minRevenuePerOrderId.take(10).foreach(println)


revenuePerOrderId.sorByKey().take(10).foreach(println)
===========================================================================

// Aggregations <--->aggregateByKey() :::::::::

aggregateByKey(zeroValue)(seqOp,combOp)[numTasks]

val orderItems=sc.textFile("/public/retail_db/order_items")
val orderItemsMap=orderItems.map(oi=>(oi.split(",")(1).toInt,oi.split(",")(4).toFloat))
//(order_id,order_item_subTotal)
val maxRevenuePerProductId=orderItemsMap.aggregateByKey((0.0,0.0))(
(inter,subtotal)=>(inter._1+subtotal, if(subtotal>inter._2) subtotal else inter._2),
(total,inter)=>(total.-1+inter._1,if(total._2>inter._2) total._2 else inter._2)
)


abc.sortByKey().take(10).foreach(println)
//(order_id,(order_revenue,max_order_item_subTotal))

Solutions:
// Aggregations - aggregateByKey
val orderItems = sc.textFile("/public/retail_db/order_items")
val orderItemsMap = orderItems.
  map(oi => (oi.split(",")(1).toInt, oi.split(",")(4).toFloat))

//(order_id, order_item_subtotal)
val revenueAndMaxPerProductId = orderItemsMap.
  aggregateByKey((0.0f, 0.0f))(
    (inter, subtotal) => (inter._1 + subtotal, if(subtotal > inter._2) subtotal else inter._2),
    (total, inter) => (total._1 + inter._1, if(total._2 > inter._2) total._2 else inter._2)
  )
//(order_id, (order_revenue, max_order_item_subtotal))

=============================================================
sortByKey():
// Sorting ---sortByKey

val products=sc.textFile("/public/retail_db/products")

val productsMap=products.map(prodcuts=>(products.split(",")(1).toInt,products))

val productsMap=products.map(product=>(product.split(",")(1).toInt,product))

val productsSortedMap=productsMap.sortByKey()

val productsSortedMap=productsMap.sortByKey(false)

val productsMap=products.map(product=>((product.split(",")(1).toInt,product.split(",")(4).toFloat),product))

val productSortedByMap=productsMap.sortByKey()


val productsMap=products.filter(product=>product.split(",")(4)!="")
.map(product=>((product.split(",")(1).toInt,product.split(",")(4).toFloat),product))

val productSortByCategory=productsMap.sortByKey().map(rec=>rec._2)
val productsMap=products.filter(product=>product.split(",")(4)=="")



//Ranking --Global (Details of top 5 products)
val products=sc.textFile("/public/retail_db/products")
val productMap=product.map()


