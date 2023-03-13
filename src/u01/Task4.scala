package u01

object Task4 extends App {

  val p1: Double => Double => Double => Boolean = x => y => z => x <= y && y == z
  val p2: (Double, Double, Double) => Boolean = (x, y, z) => x <= y && y == z
  private def p3(x: Double)(y: Double)(z: Double) : Boolean = x <= y && y == z
  private def p4(x: Double, y: Double, z: Double) : Boolean = x <= y && y == z

  println(p1(2)(4)(4)) // true
  println(p2(2, 4, 4)) // true
  println(p3(2)(4)(4)) // true
  println(p4(2, 4, 4)) // true

}
