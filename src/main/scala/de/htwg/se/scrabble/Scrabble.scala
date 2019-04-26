package de.htwg.se.scrabble

import de.htwg.se.scrabble.model.{Dictionary, Player}


object Scrabble {
  def main(args: Array[String]): Unit= {
    val player = Player("Your name")
    println("Hi " + player.name + "!")

    val dict = new Dictionary
    println(dict.alphabet)
    println(dict.dict)
  }

}
