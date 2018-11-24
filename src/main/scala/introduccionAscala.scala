import scala.io.Source._
import scala.collection._
import scala.Array._
import scala.util.Random
import breeze.linalg._
import breeze.plot._
import breeze.numerics._
import breeze.stats._
import breeze.stats.regression._



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

    def tuplesOperations() = {
      val tuple_1 = (20, "Hello", Console)
      var tupla = "hola" -> "mundo"
      val t = new Tuple3(20, "Hello", Console)
      val t2 = new Tuple4("", "", "", "")

      println(t)

      val evenTuple = (2, 4, 6, 8)

      val sumTupleElements = evenTuple._1 + evenTuple._2 + evenTuple._3 + evenTuple._4

      println("Sum of Tuple Elements: " + sumTupleElements)
      // You can also iterate over the tuple and print it's element using the foreach method

      evenTuple.productIterator.foreach { evenTuple => println("Value = " + evenTuple) }
    }

    def setOperations() = {
      // Empty set of integer type
      var sInteger: Set[Int] = Set()
      // Set of even numbers
      var sEven: Set[Int] = Set(2, 4, 8, 10)
      //Or you can use this syntax
      var sEven2 = Set(2, 4, 8, 10)
      val cities = Set("Dublin", "London", "NY", "Dublin", "Mexico")
      val tempNums: Set[Int] = Set()
      //Finding Head, Tail, and checking if the sets are empty
      println("Head of cities : " + cities.head)
      println("Tail of cities : " + cities.tail)
      println("Check if cities is empty : " + cities.isEmpty)
      println("Check if tempNums is empty : " + tempNums.isEmpty)
      val citiesEurope = Set("Dublin", "London", "NY")
      val citiesTurkey = Set("Istanbul", "Ankara", "NY")
      // Sets Concatenation using ++ operator
      var citiesConcatenated = citiesEurope ++ citiesTurkey
      println("citiesEurope ++ citiesTurkey : " + citiesConcatenated)
      //Also you can use ++ as a method
      citiesConcatenated = citiesEurope.++(citiesTurkey)
      println("citiesEurope.++(citiesTurkey) : " + citiesConcatenated)
      //Finding minimum and maximum elements in the set
      val evenNumbers = Set(2, 4, 6, 8, 8, 8)
      // Using the min and max methods
      println("Minimum element in Set(2,4,6,8) : " + evenNumbers.min)
      println("Maximum element in Set(2,4,6,8) : " + evenNumbers.max)
    }
    def listOperations()={
      val numbers = List(1, 2, 3, 4, 5, 1, 2, 3, 4, 5)
      val numbers2 = 1 :: 2 :: 3 :: 4 :: 5 :: 1 :: 2 :: 3:: 4:: 5 :: Nil

      // Empty List.
      val empty = Nil

      // Two dimensional list



      val cities = "Dublin" :: "London" :: "NY" :: Nil
      // Getting the first element in the list
      println( "Head of cities : " + cities.head )

      // Getting all the elements but the last one
      println( "Tail of cities : " + cities.tail )

      //Checking if cities/temp list is empty
      println( "Check if cities is empty : " + cities.isEmpty )

      val citiesEurope = "Dublin" :: "London" :: "Berlin" :: Nil
      val citiesTurkey = "Istanbul" :: "Ankara" :: Nil
      //Concatenate two or more lists with :::
      var citiesConcatenated = citiesEurope ::: citiesTurkey

      println( "citiesEurope ::: citiesTurkey : "+citiesConcatenated )
      // using the concat method
      citiesConcatenated = List.concat(citiesEurope, citiesTurkey)
      println( "List.concat(citiesEurope, citiesTurkey) : " + citiesConcatenated )

      val dim = 1 :: 2 :: 3 :: Nil ::
        4 :: 5 :: 6 :: Nil ::Nil
      //7 :: 8 :: 9 :: Nil :: Nil

      val dim1: List[List[Int]] =
        List(
          List(1, 0, 0,3),
          List(0, 1, 0,4),
          List(0, 0, 1,6)
        )

      println(dim)
      for (i <- 0 to 2) {
        for (j <- 0 to 2) {
          print(" " + dim1(i)(j))
        }
        println()
      }

    }

    def arrayOperations()={

      val lista1 = range(1,5)
      val valores: Array[Any] = Array[Any]("hola",2,2.3,'3',lista1)

      for (i<- valores) println(i)
      println("------------------------------------------")
      val numbers: Array[Int] = Array[Int](1, 2, 3, 4, 5, 1, 2, 3, 3, 4, 5)
      for (i <- numbers) print(i)

      var total = 0;

      for (i <- 0 to (numbers.length - 1)) {
        total = total + numbers(i)
      }
      println("\nSum: = " + total)
      //

      var myArray1 = range(5, 20, 2)
      println(myArray1(0))
      var myArray2 = range(5, 20)
      println(myArray2(0))
      //Array concatenation
      //var myArray3 = myArray1 ++ myArray2//concat( myArray1, myArray2)
      var myArray3 = concat(myArray1, myArray2)
      // Print all the array elements

      //0 +: myArray3 :+ 60
      myArray3 +:= 45
      myArray3.update(1,200)
      for ( x <- myArray3 ) {
        print(" "+ x)
      }


      println()
      var myMatrix = ofDim[Int](4,4,5)

      // Print two dimensional array

      myMatrix(0)(0)(0) = 2

      println(myMatrix.apply(1))
      for (i <- myMatrix.apply(0) ) println(i)

      for (i <- 0 to 3) {
        for (j <- 0 to 3) {
          print(" " + myMatrix(i)(j)(0))
        }
        println()
      }
    }

    //////////////////////////////////
    ////mettodos
    /////////////////////////////////

    def existOperation()={
      //UsingFatArrow =>

      // Given a list of cities and now check if "Dublin" is included in  the list
      val cityList = List("Dublin", "NY", "Cairo")
      val ifExisitsinList = cityList exists (x => x == "Dublin")

      println(ifExisitsinList)
      // Given a map of countries and their capitals check if Dublin is included in the Map
      val cityMap = Map("Ireland" -> "Dublin", "UK" -> "London")
      val ifExistsinMap = cityMap exists (x => x._2 == "Dublin")
      println(ifExistsinMap)

      println("------------------------------------------")
      println(List(1,2,3).exists(x => x < 4))
      println(List(1,4,5).exists(x => x < 4))
      println(List(10,20,30).exists(x => x < 4))

      val numbers: Array[Int] = Array[Int](1, 2, 3, 4, 5, 1, 2, 3, 3, 4, 5)
      println(numbers.exists(x => x < 4))
      println("------------------------------------------")
      println("another example: ")

      case class Complex(i: Double, j: Double) {
        def plus(other: Complex): Complex = Complex(i + other.i, j + other.j)
      }


      val obj = Complex(10, 20)
      val a = Complex(6, 9)
      val b = Complex(3, -6)
      val c = a plus b
      val z = obj.plus(a)
      println(c)
      println(z)

      /*
      (all letters)
        |
        ^
        &
        = !
        < >
        :
        + -
        * / %
        Using the infix notation for calling regular, nonsymbolic
        methods is discouraged and should be used only if it
        significantly increases readability.
       */


    }
    def optionOperation()={
      def show(x: Option[String]) = x match {
        case Some(s) => s
        case None => "?"
      }

      val megacity = Map("Bangladesh" -> "Dhaka", "Japan" -> "Tokyo", "India" -> "Kolkata", "USA" -> "New York")
      println("megacity.get( \"Bangladesh\" ) : " + show(megacity.get("Bangladesh")))
      println("megacity.get( \"India\" ) : " + show(megacity.get("India")))

      // Using getOrElse() method:
      val message: Option[String] = Some("Hello, world!")
      val x: Option[Int] = Some(20)
      val y: Option[Int] = None

      println("message.getOrElse(0): " + message.getOrElse(0))
      println("x.getOrElse(0): " + x.getOrElse("ffff"))
      println("y.getOrElse(10): " + y.getOrElse(10))

      println("message.isEmpty: " + message.isEmpty)
      println("x.isEmpty: " + x.isEmpty)
      println("y.isEmpty: " + y.isEmpty)

    }
    def flatMapOperation()={
      //Applying function on nested lists and then combining output back together
      println(List(List(2,4), List(6,8)) flatMap(x => x.map(x => x * x)))

      println(Array(Array(2,4), Array(6,8)) flatMap(x => x.map(x => x * x)))

    }
    def DropOperation()={
      // Drop the first three elements
      println("lista")
      var  ls= List(1,2,3,4,34,56) drop 3
      println(ls)

      println("Array")
      var  arr = Array(1,0,20,30,40,40,70) dropRight 3
      println(arr.toList)

      println("Map")
      val mp = Map("a" -> 10, "b" -> 15, "c" -> 1) dropWhile (x=> x._2==1)
      println(mp)

    }
    def InitOperation()={
      println("Lista")
      println(List(1,2,3,4) init)

      println("Array")
      var arr = (Array(10,20,30,40,3,4,6) init)
      for (i<-arr)  println(i)
      println("Map")
      println (Map("a" -> 10, "b" -> 15, "c" -> 1) init)


    }
    def operationGroupBy()={
      println (List(1,-2,3,-4) groupBy (x => if (x >= 0) "positive" else "negative"))

      println("List")
      var ls = List(1,-2,3,-4) groupBy (x => x >= 0)
      println (ls)

      println("Array")
      var arr = Array(2,4,5,-6,3,7,9) groupBy ( x => x%2 == 0)
      println(arr)

      println("Maps")
      val m = Map("a" -> 10, "b" -> 15, "c" -> 1) groupBy ( x => x._2%2 == 0)
      println(m)

    }
    def ForallOperation()={
      println("listas")
      println(List(1,2,3).forall(x => x < 4))

      println(List(1,5,3).forall(x => x < 4))

      println(List(10,20,30).forall(x => x < 3))

      println("Arrays")
      val numbers: Array[Int] = Array[Int](1, 2, 3, 4, 5, 1, 2, 3, 3, 4, 5)
      println(numbers.forall(x => x < 3))

      println("Maps")
      val n = Map("a" -> 10, "b" -> 15, "c" -> 16)
      println(n.forall(x => x._2 < 3))

    }

    def readFiles()={
      val lines = fromFile("C:\\Users\\Pedro\\Documents\\intelij\\ipn-workshop\\src\\main\\resources\\sales.txt").getLines
      for (i <- lines){
        val l = i.split(",").toList
        println(l,"--> ",l(0))
        println("------------------------------")
        //val m = DenseVector(i.split(","))
        //println("denseVector: ", m)
        println("------------------------------")
        val j = i.split(",").array
        println(j,"--> ",j(0).toInt)
      }
    }

    def readCSV()={
      import scala.io.Source

      def CSVReader(absPath:String, delimiter:String): List[List[Any]] = {
        println("Now reading... " + absPath)
        val MasterList = Source.fromFile(absPath).getLines().toList map {
          // String#split() takes a regex, thus escaping.
          _.split("""\""" + delimiter).toList
        }
        return MasterList
      }

      var ALHCorpus = "rep_heigth_weights.csv"
      var delimiter = "|" // I changed your delimiter to pipe since that's what's in your sample data.

      var CSVContents = CSVReader(ALHCorpus, delimiter)

      println(CSVContents(0))
    }

    def vizLines()={

      val v = DenseVector(1.0, 2.0, 3.0)
      System.out.println(v)

      val x1 = linspace(-4.0, 4.0, 200)
      val fx = sigmoid(x1)
      val fig = Figure()
      val plt = fig.subplot(0)
      plt += plot(x1, fx)
      //fig.refresh()

      val f2x = sigmoid(2.0*x1)
      val f10x = sigmoid(10.0*x1)

      plt += plot(x1, f2x, name="S(2x)")
      plt += plot(x1, f10x, name="S(10x)")

    }

    //mapsOperations()
    //tuplesOperations()
    //setOperations()
    //listOperations()
    //arrayOperations()
    //existOperation()
    //optionOperation()
    //flatMapOperation()
    //DropOperation()
    //InitOperation()
    //operationGroupBy()
    //ForallOperation()
    //readCSV()
    vizLines()
  }
}
