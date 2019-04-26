package de.htwg.se.scrabble

import java.io.FileNotFoundException

import de.htwg.se.scrabble.aview.TUI
import de.htwg.se.scrabble.model._


object Scrabble {
  var dict = new Dictionary
  val tui = new TUI

  def main(args: Array[String]): Unit= {
    try {
      tui.init()
    } catch {
      case fnf: FileNotFoundException => println(fnf.getMessage)
    }
  }
}
