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


  }
}
