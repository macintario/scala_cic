/*
* Instituto Politécnico Nacional
* Centro de Investigación en Computación
* Diplomado en Descubrimiento del Conocimiento
* con Herramientas Big Data
*
* Práctica: Modelo de Diferencias en Diferencias
*
*/


import scala.io.Source
import scala.io.Source._
import scala.collection._
import scala.Array._

import scala.reflect.ClassTag
import breeze.linalg.{DenseMatrix, DenseVector, sum}
import breeze.plot.Figure
import breeze.stats.regression.leastSquares
import breeze.stats._
import breeze.linalg._
import breeze.plot._
import breeze.numerics._
import breeze.stats._
import breeze.stats.regression._


import scala.reflect.ClassTag



object didscala {
  def main(args: Array[String]):Unit= {
    println("DID Scala")
    analiza()
  }

  // A partir del archivo de datos, se pretende encontrar los coeficientes para el modelo:
  // y = b0 + b1*T + b2*S + b3*TS

  def analiza() = {

    // Se leen los datos
    var DataDirectory = "/home/yan/IdeaProjects/scala_cic/src/main/resources/"

    val fileName = "aceleracionBruscaData.csv"

    val file = Source.fromFile(DataDirectory + fileName)

    //se descarta el encabezado
    val lines = file.getLines.toVector.drop(1)
    val splitLines = lines.map { _.split(',') }
    //Separamos las columnas
    //dado que no usaremos la fecha explicitamente ni el número de vehículo
    //guardamos en el vector de intervención sólo el 0 o el 1 si son parte
    // del grupo de control o el de intervencion y en el vector tiempo
    // si son antes o después de la intervencion
    def intervencion(s : String) = { if (s.substring(8).toInt < 21) 0.0 else 1.0}  // vehículos de 0 a 20 control, 21 en adelante intervencion
    def tiempo(s : String) = { if (s.substring(3,5).toInt < 6) 0.0 else 1.0}  // mayo o antes, sin intervencion, junio y despues intervenido
    //Convertimos las listas en DenseVectors
    def fromList[T:ClassTag](index:Int, converter:(String => T)):DenseVector[T] =
      DenseVector.tabulate(lines.size) { irow => converter(splitLines(irow)(index)) }
    //vehiculo,fecha,aceleraciones
    val vehiculos = fromList(0, elem =>  intervencion(elem) )
    val fecha = fromList(1, elem => tiempo(elem))
    val aceleraciones = fromList(2,elem =>elem.toDouble )
    //La variable independiente: Número de aceleraciones bruscas
    val indep = aceleraciones
    //Creamos un vector para el coeficiente b3, que va asociado al producto TS en el modelo lineal
    var vTS :DenseVector[Double] = DenseVector.zeros(vehiculos.length)
    for (i <- 0 to vehiculos.length-1 ){
      vTS(i) = fecha(i)*vehiculos(i)
    }
    //Se prepara la primera parte de la matriz para generar el modelo, con los valores de T
    val dep1 = DenseMatrix.horzcat(
      DenseMatrix.ones[Double](vehiculos.length, 1),
      fecha.toDenseMatrix.t
    )
    //Se agregan a la matriz los valores de S
    val dep2 = DenseMatrix.horzcat(
      dep1,
      vehiculos.toDenseMatrix.t
    )
    // Por último los valores de TS
    val dep = DenseMatrix.horzcat(
      dep2,
      vTS.toDenseMatrix.t
    )

    //Se calculan los coeficientes del modelo usando mínimos cuadrados
    val leastSquaresResult = leastSquares(dep, indep)
    println(leastSquaresResult.coefficients)
    //Se guardan en variables individuales para usarlas adelante
    val Array(b0, b1, b2, b3 )= leastSquaresResult.coefficients.toArray
    println(leastSquaresResult.rSquared)
    def y (T:Int, S:Int):Double = { b0 + b1*T + b2*S + b3*T*S }
    //Generamos tabla comparativa
    print("T=0  ")
    println(y(0,0),y(0,1))
    print("T=1  ")
    println(y(1,0),y(1,1))

    //El valor esperado del grupo de intervención sin intervencion es:
    // La salida del grupo de control en T=1 mas la diferencia (supuesta constante en el modelo)
    // entre la salida del grupo de intervencion y la salida en el de control en T =0

    val q = y(1,0) + (y(0,1) - y(0,0))
    print("Valor esperado de salida sin intervención: ")
    println(q)

    //La tasa de de crecimiento es la relación entre
    // la diferencia de la intervención
    // en el grupo intervenido
    // sobre el valor esperado sin intervención:

    val tc = (y(1,1)/q) - 1

    print("Tasa de crecimiento:")
    println(tc)

    //graficamos grupo de control
    val fig = Figure("Diferencias en diferencias")
    val plt = fig.subplot(0)
    val xPoints = linspace(0.0,1.0)
    var b = y(0,0)
    var m = y(1,0) - y(0,0)
    val s0Points = b :+ (m :* xPoints)
    plt += plot(xPoints,s0Points,colorcode = "blue",name = "Control")
    //graficamos grupo intervenido
    b = y(0,1)
    m = y(1,1) - y(0,1)
    val s1Points = b :+ (m :* xPoints)
    plt += plot(xPoints,s1Points,colorcode = "red", name ="Intervenido")
    //graficamos proyección sin intervención
    b = y(0,1)
    m = y(1,0) - y(0,0)
    val qPoints = b :+ (m :* xPoints)
    plt += plot(xPoints,qPoints,colorcode = "Green",style = '.', name = " Sin intervención ")
    plt.ylabel = "Frenadas Bruscas"
    plt.xlabel = "Tiempo: T=0 Antes de Intervencón, T=1 Después de intervención"
    plt.ylim(0, y(0,1)*1.1)
    plt.legend = true
    //mostramos la ventana con la gráfica
    fig.refresh
    println("OK")
  }

}
