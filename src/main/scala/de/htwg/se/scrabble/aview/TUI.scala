package de.htwg.se.scrabble.aview

import de.htwg.se.scrabble.controller.{Controller, GameStatus}
import de.htwg.se.scrabble.util.Observer

import scala.collection.immutable.Nil

class TUI(controller: Controller) extends Observer {
  controller.add(this)
  controller.roundManager.add(this)

  println(init)

  def init: String = artScrabble + head + help

  def artScrabble: String = """
      | .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.
      || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |
      || |    _______   | || |     ______   | || |  _______     | || |      __      | || |   ______     | || |   ______     | || |   _____      | || |  _________   | |
      || |   /  ___  |  | || |   .' ___  |  | || | |_   __ \    | || |     /  \     | || |  |_   _ \    | || |  |_   _ \    | || |  |_   _|     | || | |_   ___  |  | |
      || |  |  (__ \_|  | || |  / .'   \_|  | || |   | |__) |   | || |    / /\ \    | || |    | |_) |   | || |    | |_) |   | || |    | |       | || |   | |_  \_|  | |
      || |   '.___`-.   | || |  | |         | || |   |  __ /    | || |   / ____ \   | || |    |  __'.   | || |    |  __'.   | || |    | |   _   | || |   |  _|  _   | |
      || |  |`\____) |  | || |  \ `.___.'\  | || |  _| |  \ \_  | || | _/ /    \ \_ | || |   _| |__) |  | || |   _| |__) |  | || |   _| |__/ |  | || |  _| |___/ |  | |
      || |  |_______.'  | || |   `._____.'  | || | |____| |___| | || ||____|  |____|| || |  |_______/   | || |  |_______/   | || |  |________|  | || | |_________|  | |
      || |            1 | || |            3 | || |            1 | || |            1 | || |            3 | || |            3 | || |            1 | || |            1 | |
      || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |
      | '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'""".stripMargin

  def head: String = """
    ||                                                                                                                                                              |
    ||                                                                                                                                                              |
    ||                                                                   SCRABBLE IN SCALA                                                                          |
    ||                                                                                                                                                              |
    ||--------------------------------------------------------------------------------------------------------------------------------------------------------------|""".stripMargin

  def help: String = """
    ||                                                                                                                                                              |
    || commands                   function                                                                                                                          |
    ||                        |                                                                                                                                     |
    ||  new                   |   Play Scrabble                                                                                                                     |
    ||                        |                                                                                                                                     |
    ||  pd                    |   print dictionary - prints the dictionary to console                                                                               |
    ||                        |                                                                                                                                     |
    ||  pv                    |   print vector - prints the alphabet vector to console                                                                              |
    ||                        |                                                                                                                                     |
    ||  player [a|b] <name>   |   adds a new player with specified role (a or b) and name                                                                           |
    ||                        |                                                                                                                                     |
    ||  players               |   displays a list of the players                                                                                                    |
    ||                        |                                                                                                                                     |
    ||  help                  |   displays the command list                                                                                                         |
    ||                        |                                                                                                                                     |
    ||  exit                  |   exit scrabble                                                                                                                     |
    ||--------------------------------------------------------------------------------------------------------------------------------------------------------------|""".stripMargin


  def processCommand(com:String): Unit = {
      val command = com.split(" ")
      command(0) match {
        case "exit" => exit()
        case "help" => println(help)
        case "new" => newGame()
        case "pd" => printDict()
        case "pv" => printVector()
        case "player" => player(command)
        case "players" => players()
        case "" =>
        case unknown => System.err.println("command \'" + unknown +"\' does not exist! Use \'help\' to display commands.")
      }
  }

  def exit(): Unit = {
    System.err.println("bye!")
    System.exit(0)
  }

  def printDict(): Unit = print(controller.dictToString)

  def printVector(): Unit = print(controller.vectorToString)

  def player(parameters:Array[String]): Unit = {
    if (parameters.length == 3) {
      parameters(1) match {
        case "A" | "a" =>
          controller.newPlayer("A", parameters(2))
          println("new player A created\n")
        case "B" | "b" =>
          controller.newPlayer("B", parameters(2))
          println("new player B created\n")
        case unknown => println("parameter \'" + unknown + "\' does not exist. Use 'A' or 'B'")
      }
    } else {
      System.err.println("wrong number of arguments! use command: player [a|b] <name>")
    }
  }

  def newGame() : Unit = {
    controller.newGame()
  }

  def players(): Unit = {
    println(controller.players.toString)
  }

  @Override
  def update: Boolean = {
    println()
    println(controller.players.toString) // TODO: Spieler ausgabe (Highlight activ player)
    if (controller.players.exists("A")) {
      println("Hand player a: " + controller.players.get("A").get.getHand.toString())
    }
    if (controller.players.exists("B")) {
      println("Hand player b: " + controller.players.get("B").get.getHand.toString())
    }
    println()
    println("Cards in stack: " + controller.stack.getSize)
    println()
    print(controller.field.toString)
    println(GameStatus.message(controller.gameStatus))
    controller.gameStatus = GameStatus.IDLE
    true

  }
}
