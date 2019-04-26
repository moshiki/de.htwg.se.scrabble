package de.htwg.se.scrabble

import java.io.FileNotFoundException

import de.htwg.se.scrabble.model._


object Scrabble {
  var dict = new Dictionary

  def main(args: Array[String]): Unit= {
    try {
      TUI.start()


    } catch {
      case fnf: FileNotFoundException => println(fnf.getMessage)
    }
  }

}
