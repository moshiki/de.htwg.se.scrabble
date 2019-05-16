package de.htwg.se.scrabble

import de.htwg.se.scrabble.aview.TUI
import de.htwg.se.scrabble.controller.Controller

import scala.io.StdIn.readLine


object Scrabble {
  val controller = new Controller
  val tui = new TUI(controller)

  def main(args: Array[String]): Unit= {
      do {
        tui.processCommand(readLine(">> "))
      } while (true)
  }
}