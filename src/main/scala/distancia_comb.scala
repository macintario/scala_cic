import breeze.linalg.{DenseMatrix, DenseVector, sum}
import breeze.stats.regression.leastSquares

import scala.reflect.ClassTag

class distancia_comb {

  def main(args: Array[String]):Unit= {
    println("Hello Scala")
  }


  def cargadatos()={
    import scala.io.Source


    var DataDirectory = "/home/aulae1-b6/map-reduce/holaScala/src/main/resources/"

    val fileName = "distanciaCombustible.csv"

    val file = Source.fromFile(DataDirectory + fileName)
    val lines = file.getLines.toVector
    val splitLines = lines.map { _.split(',') }

    def fromList[T:ClassTag](index:Int, converter:(String => T)):DenseVector[T] =
      DenseVector.tabulate(lines.size) { irow => converter(splitLines(irow)(index)) }
    //vehiculo,fecha,distance,litros
    val vehiculos = fromList(0, elem => elem.toString)
    val fecha = fromList(1, elem => elem.toString)
    val distancia = fromList(2,elem =>elem.toDouble )
    val litros = fromList(3,elem => elem.toDouble)

    val indep = litros
    val dep = DenseMatrix.horzcat(
      DenseMatrix.ones[Double](distancia.length, 1),
      distancia.toDenseMatrix.t
    )
    val modeloLS = leastSquares(dep,indep)
    val Array(b, m) = modeloLS.coefficients.toArray
    println(modeloLS.coefficients)
    println("\n1.-Combustible para 100, 200, 500 km")
    // y = mx+b  l = m Km + b
    println(m*100+b)
    println(m*200+b)
    println(m*500+b)
    println("2.-Km con 200 litros:")
    // y=mx+b  => x=(y-b)/m
    println((200-b)/m)
    println("3.- Total de combustible")
    println(sum(distancia)/sum(litros))
    println("4.- Total de conbustible usado por el vehículo 10")
    val v4Vector = DenseVector.fill(vehiculos.length)("Vehiculo10")
    val v4 = (vehiculos :== v4Vector)
    print(sum(litros(v4)))
    println("\n5.-Rendimiento(km/l):")
    println(1/m)
  }

  cargadatos()

}
