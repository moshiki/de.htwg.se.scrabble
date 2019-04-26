package de.htwg.se.scrabble.model

import de.htwg.se.scrabble.Scrabble

import scala.io.StdIn

object TUI {
  def init(): Unit = {
    print(start())
    println(help())
    println(awaitingCommand())
  }


  def start(): String = """
    |########################################################################################################|
    |########################################################################################################|
    |                                                                                                        |
    |                                       SCRABBLE IN SCALA                                                |
    |                                                                                                        |"""

  def help(): String = """
    |--------------------------------------------------------------------------------------------------------|
    |                                                                                                        |
    | Commands                   Function                                                                    |
    |                        |                                                                               |
    |  rl                    |   reload dictionary - reloads the dictionary from text file                   |
    |                        |                                                                               |
    |  pd                    |   print dictionary - prints the dictionary to console                         |
    |                        |                                                                               |
    |  pv                    |   print vector - prints the alphabet vector to console                        |
    |                        |                                                                               |
    |  player [a|b] [name]   |   adds a new player with specified role (a or b) and name                     |
    |                        |                                                                               |
    |  help                  |   displays the command list                                                   |
    |                        |                                                                               |
    |  exit                  |   exit scrabble                                                               |
    |--------------------------------------------------------------------------------------------------------|"""


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
