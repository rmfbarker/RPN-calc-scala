package op

package streams

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import calc.RpnCalculator

@RunWith(classOf[JUnitRunner])
class OpSuite extends FunSuite {

  test("push") {
    val op = new PushOp(5, -1)
    val (stack, used) = op.doOp(Nil, Nil)
    assert(List(5) === stack)
    assert(List(List()) === used)

    val (undone, undoneUsed) = Undo(-1).doOp(stack, used)
    assert(Nil === undone)
    assert(Nil === undoneUsed)
  }

  test("add") {
    val inputStack: List[Double] = List(5, 2)
    val (stack, used) = Addition(-1).doOp(inputStack, Nil)
    assert(List(7) === stack)

    val (undoneStack, undoneUsed) = Undo(-1).doOp(stack, used)
    assert(inputStack === undoneStack)
  }

  test("push 5 and 6 onto stack"){
    val calc = new RpnCalculator
    val res = calc.submit("5 6")
    assert("5 6" === res)
  }
  test("add 5 and 6"){
    val calc = new RpnCalculator
    val res = calc.submit("5 6 +")
    assert("11" === res)
  }
  test("multiply 5 and 6"){
    val calc = new RpnCalculator
    val res = calc.submit("5 6 *")
    assert("30" === res)
  }
  test("multiply 5 and 6 the divide by 3"){
    val calc = new RpnCalculator
    val res = calc.submit("5 6 * 3 /")
    assert("10" === res)
  }
}
