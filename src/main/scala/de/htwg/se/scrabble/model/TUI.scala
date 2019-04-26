package de.htwg.se.scrabble.model

import de.htwg.se.scrabble.Scrabble

import scala.io.StdIn

object TUI {
  def start(): Unit = {
    println("|########################################################################################################|")
    println("|########################################################################################################|")
    println("|                                                                                                        |")
    println("| SCRABBLE IN SCALA                                                                                      |")
    println("|                                                                                                        |")
    help()
    awaitingCommand()
  }

  def help(): Unit = {
    println("|--------------------------------------------------------------------------------------------------------|")
    println("|                                                                                                        |")
    println("| Commands                                                                                               |")
    println("|                        |                                                                               |")
    println("|  rl                    |   reload dictionary - reloads the dictionary from text file                   |")
    println("|                        |                                                                               |")
    println("|  pd                    |   print dictionary - prints the dictionary to console                         |")
    println("|                        |                                                                               |")
    println("|  pv                    |   print vector - prints the alphabet vector to console                        |")
    println("|                        |                                                                               |")
    println("|  player [a|b] [name]   |   adds a new player with specified role (a or b) and name                     |")
    println("|                        |                                                                               |")
    println("|  help                  |   displays the command list                                                   |")
    println("|                        |                                                                               |")
    println("|  exit                  |   exit scrabble                                                               |")
    println("|--------------------------------------------------------------------------------------------------------|")
  }

  def awaitingCommand(): Unit = {
    while (true) {
      val command = StdIn.readLine(">> ").split(" ")
      command(0) match {
        case "exit" => System.err.println("Bye!")
                       System.exit(0)
        case "help" => help()
        case "rl" => Scrabble.dict = new Dictionary
        case "pd" => println("Word list:")
                     Scrabble.dict.dict.toStream.sorted.foreach(println)
        case "pv" => println("Alphabet vector:")
                     Scrabble.dict.alphabet.toSeq.sortBy(_._1).foreach(println)
        case "player" => println("ToDo: Player")
        case unknown => System.err.println("Command \'" + unknown +"\' does not exist! Use \'help\' to display commands.")
      }
    }
  }
}
