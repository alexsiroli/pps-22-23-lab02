package u01

import scala.annotation.tailrec

object Task6 extends App {

  @tailrec
  private def gcd(a: Int, b: Int): Int = (a, b) match
    case _ if a < b => gcd(b, a)
    case _ if b == 0 => a
    case _ => gcd(b, a % b)

  println(gcd(12, 8))  // 4
  println(gcd(14, 7))  // 7
  println(gcd(7, 14))  // 7
  println(gcd(8, 8))   // 8

}
