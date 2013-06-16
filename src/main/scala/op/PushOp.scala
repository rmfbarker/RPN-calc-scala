package op

case class PushOp(value: Double, idx: Int) extends Op(idx, value.toString) {

  def doOp(stack: Stack, usedStack: List[Stack]) = (value :: stack, stack :: usedStack)
}
