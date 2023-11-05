/*******************************************************************
 * Name: Aly Ashour
 * Date: October 29, 2023,
 * Description:
 * Main
 ******************************************************************/

import states.*

fun main() {
    // make three frames
    val f = Frame(ActiveState().start(), Data(10)) // generic start command inherited to ActiveState
    val g = Frame(IdleState().stop(), Data(20, 30)) // generic stop command inherited to IdleState
    val h = Frame(ChargingState().balance(), Data(40, 50, 60)) // ChargingState specific command .balance()

    // print three frames
    println(f); println(g); println(h)
}
