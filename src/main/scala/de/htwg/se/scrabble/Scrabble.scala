package de.htwg.se.scrabble

import java.io.FileNotFoundException

import de.htwg.se.scrabble.aview.TUI
import de.htwg.se.scrabble.model._
import scala.io.StdIn.readLine


object Scrabble {
  var dict = new Dictionary
  val tui = new TUI

  def main(args: Array[String]): Unit= {
    try {
      do {
        tui.processCommand(readLine(">> "))
      } while (true)
    } catch {
      case fnf: FileNotFoundException => println(fnf.getMessage)
    }
  }
}
