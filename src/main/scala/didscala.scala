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
    var DataDirectory = "/home/aulae1-b6/map-reduce/holaScala/src/main/resources/"

    val fileName = "aceleracionBruscaData.csv"

    val file = Source.fromFile(DataDirectory + fileName)
    val lines = file.getLines.toVector.drop(1)
    val splitLines = lines.map { _.split(',') }

    def fromList[T:ClassTag](index:Int, converter:(String => T)):DenseVector[T] =
      DenseVector.tabulate(lines.size) { irow => converter(splitLines(irow)(index)) }
    //vehiculo,fecha,aceleraciones
    val vehiculos = fromList(0, elem => elem.substring(8).toInt)
    val fecha = fromList(1, elem => elem.substring(3,5).toInt)
    val aceleraciones = fromList(2,elem =>elem.toDouble )
    //print(vehiculos)
    val vectorG = (vehiculos :> 21).toDenseVector //Vehiculos intervenidos del 21 en adelante
    val vectorT = (fecha :> 5).toDenseVector  //Fechas posteriores a mayo
    //println(vectorG)
    //println(vectorT)

    //T = 0; G = 0
    val acel_00 = aceleraciones(!vectorT(!vectorG)).toDenseVector
    //T = 0; G = 1
    val acel_01 = aceleraciones(!vectorT(vectorG)).toDenseVector
    //T = 1; G = 0
    val acel_10 = aceleraciones(vectorT(!vectorG)).toDenseVector
    //T = 1; G = 1
    val acel_11 = aceleraciones(vectorT(vectorG)).toDenseVector



    val bet_0 = mean(acel_00)
    println(bet_0)
    val bet_1 = mean(acel_10) - bet_0
    println(bet_1)
    val bet_2 = mean(acel_10) - bet_0
    println(bet_2)
    val bet_3 = (mean(acel_11) - mean(acel_01))
        - (mean(acel_10)-mean(acel_00))
    println(bet_3)

    println("OK")
  }
  analiza()
}
