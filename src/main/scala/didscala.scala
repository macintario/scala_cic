import scala.io.Source
import scala.reflect.ClassTag

object didscala {
  def main(args: Array[String]):Unit= {
    println("DID Scala")
  }
  def analiza() = {
    var DataDirectory = "/home/aulae1-b6/map-reduce/holaScala/src/main/resources/"

    val fileName = "distanciaCombustible.csv"

    val file = Source.fromFile(DataDirectory + fileName)
    val lines = file.getLines.toVector
    val splitLines = lines.map { _.split(',') }

    def fromList[T:ClassTag](index:Int, converter:(String => T)):DenseVector[T] =
      DenseVector.tabulate(lines.size) { irow => converter(splitLines(irow)(index)) }
    //vehiculo,fecha,aceleraciones

    println("OK")
  }
  analiza()
}
