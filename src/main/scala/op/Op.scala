package op

abstract class Op(idx: Int, symbol: String) {

  /**
   * Execute the operation
   *
   * @param stack the current stack
   * @return the stack after this operation has been executed
   */
  def doOp(stack: Stack, usedStack: List[Stack]): (Stack, List[Stack])

  def index: Int = idx

}

case class UnknownOp(idx: Int, symbol: String) extends Op(idx, symbol) {

  def doOp(stack: Stack, usedStack: List[Stack]) = {
    println("unknown operator: '" + symbol + "'")
    (stack, usedStack)
  }

}

case class Undo(idx: Int) extends Op(idx, "undo") {

  def doOp(stack: Stack, usedStack: List[Stack]) = (usedStack.head, usedStack.tail)

}

case class ClearOp(idx: Int) extends Op(idx, "clear") {

  def doOp(stack: Stack, usedStack: List[Stack]) = (Nil, stack :: usedStack)

}

