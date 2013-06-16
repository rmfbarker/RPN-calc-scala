import calc.RpnCalculator

object Main extends App {

  println("Welcome to the RPN calculator")

  var input: String = null
  val calc = new RpnCalculator
  do {
    input = readLine()
    println ("stack: " + calc.submit(input))
  } while (input != "exit")

}
