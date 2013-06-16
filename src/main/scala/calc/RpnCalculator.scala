package calc

import op._
import java.text.DecimalFormat
import java.math.RoundingMode

class RpnCalculator extends Calculator {

  type CalcState = (Stack, List[Stack])

  var calcState: CalcState = (List(), List())

  def submit(input: String): String = {
    val ops = parse(input.split(" ").toList)

    def iter(ops: List[Op], state: CalcState): CalcState = {
      ops match {
        case Nil => state
        case x :: xs => try {
          iter(xs, x.doOp(state._1, state._2))
        } catch {
          case ex: InsufficientOperandsException => println(ex.getMessage); state
        }
      }
    }

    calcState = iter(ops, calcState)

    calcState._1.reverse map (fmtDouble(_)) mkString (" ")
  }

  def parse(args: List[String]): List[Op] = {
    def iter(args: List[String], idx: Int): List[Op] = args match {
      case Nil => Nil
      case "" :: xs => iter(args.tail, idx + 1)
      case x :: xs => parseArg(x.trim(), idx) :: iter(xs, idx + x.length)
    }
    iter(args, 0)
  }

  def parseArg(arg: String, idx: Int): Op = arg match {
    case "+" => Addition(idx)
    case "-" => Subtraction(idx)
    case "*" => Multiplication(idx)
    case "/" => Division(idx)
    case "clear" => ClearOp(idx)
    case "sqrt" => Sqrt(idx)
    case "undo" => Undo(idx)
    case num if (isNumeric(num)) => PushOp(num.toDouble, idx)
    case o => UnknownOp(idx, o)
  }

  def isNumeric(input: String): Boolean = input.matches("[-+]?\\d+(\\.\\d+)?")

  def fmtDouble(number: Double) = {
    val fmt = new DecimalFormat("0.##########")
    fmt.setRoundingMode(RoundingMode.DOWN)

    if (number.isNaN || number.isInfinite) number.toString
    else fmt.format(number)
  }
}
