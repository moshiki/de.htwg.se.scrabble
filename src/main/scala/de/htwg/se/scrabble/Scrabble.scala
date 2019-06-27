package de.htwg.se.scrabble

import de.htwg.se.scrabble.aview.TUI
import de.htwg.se.scrabble.controller.controllerBaseImpl.Controller

import scala.io.StdIn.readLine


object Scrabble {
 // val controller = Controller
  val tui = new TUI(Controller)

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