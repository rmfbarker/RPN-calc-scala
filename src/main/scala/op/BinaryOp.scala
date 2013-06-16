package op

/**
 * Base class for all operations which take two operands. Only the actual arithmetic is left to be implemented by the
 * base class.
 */
sealed abstract class BinaryOp(f: (Double, Double) => Double, idx: Int, symbol: String) extends Op(idx, symbol) {

  override def doOp(stack: Stack, used: List[Stack]) = stack match {
    case op1 :: op2 :: rest => {
      (f(op2, op1) :: rest, stack :: used)
    }
    case _ => throw new InsufficientOperandsException("operator " + symbol + " (position: " + idx + "): insufficient parameters")
  }

}

case class Addition(idx: Int) extends BinaryOp(_ + _, idx, "+")

case class Subtraction(idx: Int) extends BinaryOp(_ - _, idx, "-")

case class Multiplication(idx: Int) extends BinaryOp(_ * _, idx, "*")

case class Division(idx: Int) extends BinaryOp(_ / _, idx, "/")
