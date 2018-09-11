package batch17.pack



import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._
import java.util.Properties
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import scala.io.Source
import java.io.File
//object sparkHive extends App with SparkConfParam{

  object sparkHive {

    def main(args: Array[String]){
      val warehouseLocation = new File("spark-warehouse").getAbsolutePath
 val Conf = new SparkConf()
    .setAppName("Learn Spark")
    .setMaster("local")
    .set("spark.cores.max", "4")
    .set("spark.sql.parquet.binaryAsString","true")
  val spark = SparkSession
    .builder()
    .config(Conf)
    .config("spark.sql.warehouse.dir", warehouseLocation)
    .enableHiveSupport()
    .getOrCreate()

println("warehouseLocation : " + warehouseLocation)

  spark.sql("CREATE TABLE IF NOT EXISTS employee17(id INT, name STRING, age INT) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n'")

  import spark.implicits._
  import spark.sql

  spark.sql("LOAD DATA LOCAL INPATH '/usr/hdp/2.6.4.0-91/spark2/bin/employee.txt' INTO TABLE employee17")

  spark.sql("FROM employee17 SELECT id, name, age").show()
}
}


/*
object sample {
  def main(args: Array[String]): Unit =
  {
    println("this is object1")
for(i <- 1 to 10)
{
println("number : " + i)
}
  }
}
*/
