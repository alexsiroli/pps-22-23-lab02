package u01

import scala.math.*

object Task7 extends App {

  case class Point2D(x: Double, y: Double)

  /***
   *Method that returns true if the first parameter is the internal value of the other two
   *
   * @param d value to check
   * @param left left margin
   * @param right right margin
   * @return true if d is internal value of left and right
   */
  private def between(d: Double, left: Double, right: Double) : Boolean = d >= left && d <= right

  /***
   *Method that checks if a point is inside a rectangle given the lower left and upper right corners.
   *
   * @param p point to check
   * @param lowerLeft lower left corner
   * @param upperRight upper right conrner
   * @return true if p is the interior point of the rectangle
   */
  private def between(p: Point2D, lowerLeft: Point2D, upperRight: Point2D) : Boolean =
    between(p.x, lowerLeft.x, upperRight.x) && between(p.y, lowerLeft.y, upperRight.y)

  /***
   *Method that raises a double to the power of two.
   *
   * @param d value to calculate
   * @return d ^2^
   */
  private def pow2(d: Double) : Double = pow(d, 2)

  /***
   *Method that calculates the distance between two points.
   *
   * @param p1 first point
   * @param p2 secondo point
   * @return the distance between p1 and p2
   */
  private def distance(p1: Point2D, p2: Point2D) : Double =
    sqrt(pow2(p1.x - p2.x) + pow2(p1.y - p2.y))

  private enum Shape:
    case Square(bottomLeft: Point2D, side: Double)
    case Circle(center: Point2D, radius: Double)
    case Rectangle(bottomLeft: Point2D, upRight: Point2D)

  private def perimeter(shape: Shape) : Double = shape match
    case Shape.Square(_, s) => s * 4
    case Shape.Circle(_, r) => 2 * r * Pi
    case Shape.Rectangle(p1, p2) => 2 * (p2.x - p1.x) + 2 * (p2.y - p1.y)

  private def contatins(shape: Shape, point: Point2D) : Boolean = (shape, point) match
    case (Shape.Square(p, s), point) => between(point, p, Point2D(p.x + s, p.y + s))
    case (Shape.Circle(c, r), point) => distance(c, point) <= r
    case (Shape.Rectangle(p1, p2), point) => between(point, p1, p2)

  private val square = Shape.Square(Point2D(0,0), 2)
  println(perimeter(square))  // 8
  println(contatins(square, Point2D(1, 1))) // true
  println(contatins(square, Point2D(2, 2))) // true
  println(contatins(square, Point2D(0, 3))) // false
  println()

  private val circle = Shape.Circle(Point2D(0,0), 3)
  println(perimeter(circle))  // 18.84...
  println(contatins(circle, Point2D(1, 2))) // true
  println(contatins(circle, Point2D(0, 3))) // true
  println(contatins(circle, Point2D(3, 3))) // true
  println()

  private val rectangle = Shape.Rectangle(Point2D(0,0), Point2D(3,2))
  println(perimeter(rectangle))  // 10
  println(contatins(rectangle, Point2D(1,2))) // true
  println(contatins(rectangle, Point2D(0,0))) // true
  println(contatins(rectangle, Point2D(-1,0))) // false

}
