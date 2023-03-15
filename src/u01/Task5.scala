package u01

object Task5 extends App {

  private val composeByVal: (Int => Int, Int => Int) => Int => Int = (f, g) => i => f(g(i))
  println(composeByVal(_ - 1, _ * 2)(5)) // 9

  private def composeByDef(f: Int => Int, g: Int => Int): Int => Int = x => f(g(x))
  println(composeByDef(_ - 1, _ * 2)(5)) // 9

  private def genericCompose[X](f: X => X, g: X => X): X => X = x => f(g(x))
  val f: Double => Double = d => d/2
  println(genericCompose(f, f)(1)) // 0.25

}
