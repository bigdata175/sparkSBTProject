package batch17.pack

import java.util.Properties
import scala.io.Source
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

object prog2 {
  def main(args: Array[String]) {

    val conf = new SparkConf()
      .setAppName("prog2")
      .setMaster("local")
    val sc = new SparkContext(conf)
    val RDD1 = sc.parallelize(List("abc", "def", "ghi", "abc", "def", "abc"))
    val RDD2 = RDD1.flatMap(_.split(",")).map(x => (x, 1)).reduceByKey((x,y) => (x+y))
    RDD2.saveAsTextFile("/input/file2")
    val y = sc.textFile("/input/file2")
    println(y.collect().mkString(", "))

  }
}
