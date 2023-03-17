package u01

import scala.annotation.tailrec
import scala.math.*

object Lab02 extends App {

  // Utils
  def task(n: Int) = println("\n-- Task " + n + " --\n")
  def newLine() = println()

  def assertEquals[A](exp: A)(act: A) = exp match
    case _ if exp == act => println("✔ Expected: " + exp + ", Actual: " + act)
    case _ => println("✘ Expected: " + exp + ", Actual: " + act)
  def assertTrue(act: Boolean) = assertEquals(true)(act)
  def assertFalse(act: Boolean) = assertEquals(false)(act)


  // Task 1 - svolto da solo
  task(1)
  println("Hello, Scala")
  newLine()


  // Task 3 - svolto da solo
  task(3)

  // a)
  val posVal: Int => String = _ match
    case x if x >= 0 => "positive"
    case _ => "negative"

  assertEquals("positive")(posVal(3))
  assertEquals("positive")(posVal(0))
  assertEquals("negative")(posVal(-3))
  newLine()

  def posDef(i: Int): String = i match
    case x if x >= 0 => "positive"
    case _ => "negative"

  assertEquals("positive")(posDef(3))
  assertEquals("positive")(posDef(0))
  assertEquals("negative")(posDef(-3))
  newLine()

  // b)
  val negVal: (String => Boolean) => String => Boolean = f => s => !f(s)
  def negDef(f: String => Boolean) : String => Boolean = s => !f(s)

  val empty: String => Boolean = _ == ""
  val notEmptybyVal = negVal(empty)
  val notEmptybyDef = negDef(empty)

  assertTrue(notEmptybyVal("foo"))
  assertFalse(notEmptybyVal(""))
  assertTrue(notEmptybyVal("foo") && !notEmptybyVal(""))
  newLine()

  assertTrue(notEmptybyDef("foo"))
  assertFalse(notEmptybyDef(""))
  assertTrue(notEmptybyDef("foo") && !notEmptybyDef(""))
  newLine()

  // c)
  private def genericNeg[X](f: X => Boolean): X => Boolean = s => !f(s)

  private val positive: Int => Boolean = _ >= 0
  private val negative = genericNeg(positive)

  assertTrue(negative(-3))
  assertFalse(negative(3))
  assertTrue(negative(-4) && positive(0))
  newLine()


  // Task 4 - svolto da solo
  task(4)

  val p1: Double => Double => Double => Boolean = x => y => z => x <= y && y == z

  assertTrue(p1(2)(4)(4))
  assertFalse(p1(2)(4)(5))
  newLine()

  val p2: (Double, Double, Double) => Boolean = (x, y, z) => x <= y && y == z

  assertTrue(p2(2, 4, 4))
  assertFalse(p2(2, 4, 5))
  newLine()

  def p3(x: Double)(y: Double)(z: Double): Boolean = x <= y && y == z

  assertTrue(p3(2)(4)(4))
  assertFalse(p3(2)(4)(5))
  newLine()

  def p4(x: Double, y: Double, z: Double): Boolean = x <= y && y == z

  assertTrue(p4(2, 4, 4))
  assertFalse(p4(2, 4, 5))
  newLine()


  // Task 5 - svolto da solo
  task(5)

  val composeByVal: (Int => Int, Int => Int) => Int => Int = (f, g) => i => f(g(i))

  assertEquals(9)(composeByVal(_ - 1, _ * 2)(5))

  def composeByDef(f: Int => Int, g: Int => Int): Int => Int = x => f(g(x))

  assertEquals(9)(composeByDef(_ - 1, _ * 2)(5))

  def genericCompose[X, Y, Z](f: Y => Z, g: X => Y): X => Z = x => f(g(x))

  val g: Int => Double = i => i /  2.0
  val f: Double => String = d => String.valueOf(d)
  assertEquals(String.valueOf(0.5))(genericCompose(f, g)(1))
  newLine()


  // Task 6 - svolto da solo
  task(6)

  @tailrec
  private def gcd(a: Int, b: Int): Int = a match
    case _ if b == 0 => a
    case _ => gcd(min(a, b), max(a, b) % min(a, b))

  assertEquals(4)(gcd(12, 8))
  assertEquals(7)(gcd(14, 7))
  assertEquals(7)(gcd(7, 14))
  assertEquals(8)(gcd(8, 8))
  newLine()


  // Task 7 - svolto da solo
  task(7)

  case class Point2D(x: Double, y: Double)

  /** *
   * Method that returns true if the first parameter is the internal value of the other two
   *
   * @param d     value to check
   * @param left  left margin
   * @param right right margin
   * @return true if d is internal value of left and right
   */
  def between(d: Double, left: Double, right: Double): Boolean = d >= left && d <= right

  assertTrue(between(2, 1, 3))
  assertTrue(between(0, 0, 0))
  assertFalse(between(3, 0, 2))
  newLine()

  /** *
   * Method that checks if a point is inside a rectangle given the lower left and upper right corners.
   *
   * @param p          point to check
   * @param lowerLeft  lower left corner
   * @param upperRight upper right conrner
   * @return true if p is the interior point of the rectangle
   */
  def between(p: Point2D, lowerLeft: Point2D, upperRight: Point2D): Boolean =
    between(p.x, lowerLeft.x, upperRight.x) && between(p.y, lowerLeft.y, upperRight.y)

  assertTrue(between(Point2D(1, 2), Point2D(0, 0), Point2D(2, 2)))
  assertTrue(between(Point2D(0, 0), Point2D(0, 0), Point2D(0, 0)))
  assertFalse(between(Point2D(0, 0), Point2D(1, 1), Point2D(2, 2)))
  newLine()

  /** *
   * Method that raises a double to the power of two.
   *
   * @param d value to calculate
   * @return d ^2^
   */
  def pow2(d: Double): Double = pow(d, 2)

  assertEquals(4)(pow2(-2))
  assertEquals(0)(pow2(0))
  assertEquals(9)(pow2(3))
  newLine()

  /** *
   * Method that calculates the distance between two points.
   *
   * @param p1 first point
   * @param p2 secondo point
   * @return the distance between p1 and p2
   */
  def distance(p1: Point2D, p2: Point2D): Double =
    sqrt(pow2(p1.x - p2.x) + pow2(p1.y - p2.y))

  assertEquals(4)(distance(Point2D(0, 0), Point2D(4, 0)))
  assertEquals(4)(distance(Point2D(0, 0), Point2D(0, 4)))
  newLine()

  enum Shape:
    case Square(bottomLeft: Point2D, side: Double)
    case Circle(center: Point2D, radius: Double)
    case Rectangle(bottomLeft: Point2D, upRight: Point2D)

  object Shape:
    def perimeter(shape: Shape): Double = shape match
      case Square(_, s) => s * 4
      case Circle(_, r) => 2 * r * Pi
      case Rectangle(p1, p2) => 2 * (p2.x - p1.x) + 2 * (p2.y - p1.y)

    def contatins(shape: Shape, point: Point2D): Boolean = (shape, point) match
      case (Square(p, s), point) => between(point, p, Point2D(p.x + s, p.y + s))
      case (Circle(c, r), point) => distance(c, point) <= r
      case (Rectangle(p1, p2), point) => between(point, p1, p2)

  import Shape.*

  val square = Square(Point2D(0, 0), 2)
  assertEquals(8)(perimeter(square))
  assertTrue(contatins(square, Point2D(1, 1)))
  assertTrue(contatins(square, Point2D(2, 2)))
  assertFalse(contatins(square, Point2D(0, 3)))
  newLine()

  val circle = Circle(Point2D(0, 0), 3)
  assertEquals(3 * 2 * Pi)(perimeter(circle))
  assertTrue(contatins(circle, Point2D(1, 2)))
  assertTrue(contatins(circle, Point2D(0, 3)))
  assertFalse(contatins(circle, Point2D(3, 3)))
  newLine()

  val rectangle = Rectangle(Point2D(0, 0), Point2D(3, 2))
  assertEquals(10)(perimeter(rectangle))
  assertTrue(contatins(rectangle, Point2D(1, 2)))
  assertTrue(contatins(rectangle, Point2D(0, 0)))
  assertFalse(contatins(rectangle, Point2D(-1, 0)))
  newLine()


  // task 8 - svolto da solo
  task(8)

  enum Option[A]:
    case Some(a: A)
    case None()

  object Option:

    def isEmpty[A](opt: Option[A]): Boolean = opt match
      case None() => true
      case _ => false

    def orElse[A, B >: A](opt: Option[A], orElse: B): B = opt match
      case Some(a) => a
      case _ => orElse

    def flatMap[A, B](opt: Option[A])(f: A => Option[B]): Option[B] = opt match
      case Some(a) => f(a)
      case _ => None()

    def filter[A](opt: Option[A])(p: A => Boolean): Option[A] = opt match
      case Some(a) if p(a) => opt
      case _ => None()

    def map[A, B](opt: Option[A])(f: A => B): Option[B] = opt match
      case Some(a) => Some(f(a))
      case _ => None()

    def fold[A](opt: Option[A])(dv: A)(f: A => A): A = opt match
      case Some(a) => f(a)
      case _ => dv

  import Option.*

  val s1: Option[Int] = Some(1)
  val s2: Option[Int] = Some(2)
  val s3: Option[Int] = None()

  assertEquals(Some(1))(s1)
  assertEquals(1)(orElse(s1, 0))
  assertEquals(0)(orElse(s3, 0))
  assertEquals(Some(2))(flatMap(s1)(i => Some(i + 1)))
  assertEquals(Some(3))(flatMap(s1)(i => flatMap(s2)(j => Some(i + j))))
  assertEquals(None())(flatMap(s1)(i => flatMap(s3)(j => Some(i + j))))
  newLine()

  assertEquals(Some(5))(filter(Some(5))(_ > 2))
  assertEquals(None())(filter(Some(5))(_ > 8))
  assertEquals(None())(filter(None[Int]())(_ > 2))
  newLine()

  assertEquals(Some(true))(map(Some(5))(_ > 2))
  assertEquals(Some(false))(map(Some(5))(_ > 8))
  assertEquals(None())(map(None[Int]())(_ > 2))
  newLine()

  assertEquals(6)(fold(Some(5))(1)(_ + 1))
  assertEquals(1)(fold(None[Int]())(1)(_ + 1))
}