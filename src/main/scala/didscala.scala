import scala.io.Source
import scala.reflect.ClassTag
import breeze.linalg.{DenseMatrix, DenseVector, sum}
import breeze.stats.regression.leastSquares
import breeze.stats._

import scala.reflect.ClassTag



object didscala {
  def main(args: Array[String]):Unit= {
    println("DID Scala")
  }
  def analiza() = {
//    var DataDirectory = "/home/aulae1-b6/map-reduce/holaScala/src/main/resources/"
    var DataDirectory = "/home/yan/Descargas/MapReduce-master/scala_cic/src/main/resources/"

    val fileName = "aceleracionBruscaData.csv"

    val file = Source.fromFile(DataDirectory + fileName)
    val lines = file.getLines.toVector.drop(1)
    val splitLines = lines.map { _.split(',') }
    def intervencion(s : String) = { if (s.substring(8).toInt < 21) 0.0 else 1.0}  // vehículos de 0 a 20 control, 21 en adelante intervencion

    def tiempo(s : String) = { if (s.substring(3,5).toInt < 6) 0.0 else 1.0}  // mayo o antes, sin intervencion, junio y despues intervenido
//    def isControl(s : String) = { s.substring(8) }

    def fromList[T:ClassTag](index:Int, converter:(String => T)):DenseVector[T] =
      DenseVector.tabulate(lines.size) { irow => converter(splitLines(irow)(index)) }

    //vehiculo,fecha,aceleraciones
    val vehiculos = fromList(0, elem =>  intervencion(elem) )
    val fecha = fromList(1, elem => tiempo(elem))
    val aceleraciones = fromList(2,elem =>elem.toDouble )

    val indep = aceleraciones

    var vTS :DenseVector[Double] = DenseVector.zeros(vehiculos.length)
    for (i <- 0 to vehiculos.length-1 ){
      vTS(i) = fecha(i)*vehiculos(i)
    }

    val dep1 = DenseMatrix.horzcat(
      DenseMatrix.ones[Double](vehiculos.length, 1),
      fecha.toDenseMatrix.t
    )
    val dep2 = DenseMatrix.horzcat(
      dep1,
      vehiculos.toDenseMatrix.t
    )
    val dep = DenseMatrix.horzcat(
      dep2,
      vTS.toDenseMatrix.t
    )
    val leastSquaresResult = leastSquares(dep, indep)
    println(leastSquaresResult.coefficients)
    println(leastSquaresResult.rSquared)

    //println(dep)
    //println(fecha)


    println("OK")
  }
  analiza()
}
