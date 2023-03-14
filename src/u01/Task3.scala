package u01

object Task3 extends App {

  // a)
  private val pos: Int => String = _ match
    case x if x >= 0 => "positive"
    case _ => "negative"
  /**
  private def pos(i: Int) : String = i match
    case x if x >= 0 => "positive"
    case _ => "negative"
  */

  println(pos(3))   // positive
  println(pos(0))   // positive
  println(pos(-3))  // negative
  println()

  // b)
  private val neg : (String => Boolean) => String => Boolean = f => s => !f(s)
  //private def neg(f: String => Boolean) : String => Boolean = s => !f(s)

  private val empty: String => Boolean = _ == ""
  private val notEmpty = neg(empty)

  println(notEmpty("foo"))  // true
  println(notEmpty(""))     // false
  println(notEmpty("foo") && !notEmpty(""))   // true
  println()

  // c)
  private def genericNeg[X](f: X => Boolean) : X => Boolean = s => !f(s)

  private val positive: Int => Boolean = _ >= 0
  private val negative = genericNeg(positive)

  println(negative(-3))   // true
  println(negative(3))    // false
  println(negative(-4) && positive(0))  // true
  println()

}
