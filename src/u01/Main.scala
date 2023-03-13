package u01

object Main extends App {

  println("Hello, Scala")

  private val positive1: Int => String = _ match
    case x if x >= 0 => "positive"
    case _ => "negative"

  println(positive1(3))
  println(positive1(0))
  println(positive1(-3))

  private def positive2: Int => String = _ match
    case x if x >= 0 => "positive"
    case _ => "negative"

  println(positive2(3))
  println(positive2(0))
  println(positive2(-3))

}
