package de.htwg.se.scrabble

import de.htwg.se.scrabble.model._


object Scrabble {
  var dict = new Dictionary

  def main(args: Array[String]): Unit= {
    //val player = Player("Your name")
    //println("Hi " + player.name + "!")


    //println(dict.alphabet)
    //println(dict.dict)

    TUI.start()
  }

}
