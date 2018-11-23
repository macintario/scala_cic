object introduccionAscala {
  def main(args: Array[String]):Unit={
    println("Hello Scala")
    val tupla = (a: Int) => (a, a+2)
    println(tupla(1))
    println(tupla(1)._2)
    println(tupla(1)._1)

    val y = 10
    var x= 10
    x = 15
    val w = 10e20
    val m:Byte = 127
    val bool = !false
    var x1 = 'X'
    var person: Any = "Craig"
    person = 22

    val bookTitle = "Beginning with Scala"
    println(s"Book Title is $bookTitle")

    def hello() = {"Hello world"}
    //hello()
    def square (i:Int) = {i*i}
    println(square(100))

    val books2: List[String] = List("Beginning Scala", "Beginning Groovy", "Beginning Java")
    println(books2)

    if(x.equals(10)){
      println("Line one")
      println("Line two")
    }else{
      println(x)
    }
    while(!bool){
      println("Working ....")
    }

    for(book<-books2) println(book)

    for(book<-books2
      if book.contains("Scala")
    )println(book)

    for {book2 <- books2
         bookVal = book2.toUpperCase()
    }println(bookVal)

    val res = 44 match {

      case 44 => true // if wematch 44,theresult is true

      case _ => false // otherwisetheresult isfalse
    }
    println("match")
    println(res)

    /////////////////////////////////
    //// Programacion funcional///////
    /////////////////////////////////
    def functionalProgramming() = {
      //asignar una fucion a una variable
      val doubler = (i: Int) => {
        i * 2
      }
      println(doubler(2))
    }

    //pasar funciones a otras funciones
    def operation(functionparam:(Int, Int) => Int) {
      println(functionparam(4,4))
    }

    val add = (x: Int, y:Int) => { x + y }

    operation(add)

    val multiply = (x: Int, y:Int) => { x * y }

    operation(multiply)

    val subtract = (x: Int, y:Int) => { x - y }

    operation(subtract)

    //
    // //retornar fuciones
    def greeting() = (name: String) => {"hello" + " " + name}
    //
    val greet = greeting()
    //
    println(greet("Reader"))

  }
}
