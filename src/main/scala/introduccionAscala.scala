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



  }
}
