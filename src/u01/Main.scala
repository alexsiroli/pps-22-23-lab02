package u01

object Main extends App {

  // Hello World
  println("Hello, Scala")
  println()

  // task 3a
  private val pos: Int => String = _ match
    case x if x >= 0 => "positive"
    case _ => "negative"
  /**
  private def pos: Int => String = _ match
    case x if x >= 0 => "positive"
    case _ => "negative"
  */

  println(pos(3))
  println(pos(0))
  println(pos(-3))
  println()

  // Task 3b
  private val neg : (String => Boolean) => String => Boolean = f => s => !f(s)
  //private def neg : (String => Boolean) => (String => Boolean) = f => (s => !f(s))

  private val empty: String => Boolean = _ == ""
  private val notEmpty = neg(empty)
  println(notEmpty("foo"))
  println(notEmpty(""))
  println(notEmpty("foo") && !notEmpty(""))
  println()

}
