package op

case class Sqrt(idx: Int) extends Op(idx, "sqrt") {

  def doOp(stack: Stack, usedStack: List[Stack]) = stack match {
    case Nil => throw new RuntimeException("insufficient arguments for sqrt")
    case x :: xs => (Math.sqrt(x) :: xs, stack :: usedStack)
  }

}
