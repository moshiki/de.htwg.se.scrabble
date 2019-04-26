package de.htwg.se.scrabble

import java.io.FileNotFoundException

import de.htwg.se.scrabble.aview.TUI
import de.htwg.se.scrabble.model._


object Scrabble {
  var dict = new Dictionary

  def main(args: Array[String]): Unit= {
    try {
      TUI.init()
    } catch {
      case fnf: FileNotFoundException => println(fnf.getMessage)
    }
  }

}
