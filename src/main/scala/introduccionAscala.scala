import scala.io.Source._
import scala.collection._
import scala.Array._
import breeze.plot



object introduccionAscala {
  def main(args: Array[String]):Unit= {
    println("Hello Scala")

    def mapsOperations() = {

      val n = Map("a" -> 10, "b" -> 15, "c" -> 16)

      val m = Map(("a", 10), ("b", 15), ("c", 16))

      for (i <- n) println(i)
      println("----------------------")

      var myArray = range(5, 20, 2)

      for (i <- myArray) print(i)

      def getMax(): Int = {
        // Finding the largest element
        var max = myArray(0)
        for (i <- 1 to (myArray.length - 1)) {
          if (myArray(i) > max)
            max = myArray(i)
        }
        max
      }

      println("____________________________")
      val capitals = Map("Ireland" -> "Dublin", "Britain" -> "London", "Germany" -> "Berlin")
      val temp: Map[Int, Int] = Map()
      val myMax = Map("getMax" -> getMax())

      println("My max is: " + myMax)

      println("Keys in capitals : " + capitals.keys)
      println("Values in capitals : " + capitals.values)
      println("Check if capitals is empty : " + capitals.isEmpty)
      println("Check if temp is empty : " + temp.isEmpty)

      val capitals1 = Map("Ireland" -> "Dublin", "Turkey" -> "Ankara", "Egypt" -> "Cairo")
      val capitals2 = Map("Germany" -> "Berlin", "Saudi Arabia" -> "Riyadh")
      // Map concatenation using ++ operator
      var capitalsConcatenated = capitals1 ++ capitals2
      println("capitals1 ++ capitals2 : " + capitalsConcatenated)

      // use two maps with ++ as method
      capitalsConcatenated = capitals1.++(capitals2)
      println("capitals1.++(capitals2)) : " + capitalsConcatenated)
    }
    mapsOperations()
  }
}
