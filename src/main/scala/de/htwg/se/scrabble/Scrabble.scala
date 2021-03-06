package de.htwg.se.scrabble

import com.google.inject.Guice
import de.htwg.se.scrabble.aview.TUI
import scala.io.StdIn.readLine
import de.htwg.se.scrabble.controller.ControllerInterface

object Scrabble {
  val injector = Guice.createInjector(new ScrabbleModule)
  val controller = injector.getInstance(classOf[ControllerInterface])
//  val controller = new Controller
  val tui = new TUI(controller:ControllerInterface)

  def main(args: Array[String]): Unit= {
    var input: String = ""
    if (args.length>0) input=args(0)
    if (!input.isEmpty) tui.processCommand(input)
    else do {

      input = readLine(">> ")
      tui.processCommand(input)
    } while (input != "q")
  }
}