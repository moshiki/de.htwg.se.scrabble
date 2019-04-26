package de.htwg.se.scrabble.aview

import de.htwg.se.scrabble.controller.Controller
import de.htwg.se.scrabble.model.Player
import de.htwg.se.scrabble.util.Observer

class TUI(controller: Controller) extends Observer {
  controller.add(this)
  init()

  def init(): Unit = {
    print(artScrabble())
    print(head())
    println(help())
  }

  def artScrabble(): String =
    """
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
      | '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'
    """.stripMargin

  def head(): String = """
    |########################################################################################################|
    |########################################################################################################|
    |                                                                                                        |
    |                                       SCRABBLE IN SCALA                                                |
    |                                                                                                        |"""

  def help(): String = """
    |--------------------------------------------------------------------------------------------------------|
    |                                                                                                        |
    | commands                   function                                                                    |
    |                        |                                                                               |
    |  rl                    |   reload dictionary - reloads the dictionary from text file                   |
    |                        |                                                                               |
    |  pd                    |   print dictionary - prints the dictionary to console                         |
    |                        |                                                                               |
    |  pv                    |   print vector - prints the alphabet vector to console                        |
    |                        |                                                                               |
    |  player [a|b] <name>   |   adds a new player with specified role (a or b) and name                     |
    |                        |                                                                               |
    |  help                  |   displays the command list                                                   |
    |                        |                                                                               |
    |  exit                  |   exit scrabble                                                               |
    |--------------------------------------------------------------------------------------------------------|"""


  def processCommand(com:String): Unit = {
      val command = com.split(" ")
      command(0) match {
        case "exit" => exit()
        case "help" => println(help())
        case "rl" => reloadDict()
        case "pd" => printDict()
        case "pv" => printVector()
        case "player" => player(command)
        case unknown => System.err.println("command \'" + unknown +"\' does not exist! Use \'help\' to display commands.")
      }
  }

  def exit(): Unit = {
    System.err.println("bye!")
    System.exit(0)
  }

  def reloadDict(): Unit = {
    controller.reloadDict()
    println("dictionary reloaded")
  }

  def printDict(): Unit = controller.printDict()

  def printVector(): Unit = controller.printVector()

  def player(parameters:Array[String]): Unit = {
    if (parameters.length == 3) {
      var player: Player = null
      parameters(1) match {
        case "A" | "a" => player = controller.newPlayer("A", parameters(2))
        case "B" | "b" => player = controller.newPlayer("B", parameters(2))
        case unknown => println("parameter \'" + unknown + "\' does not exist. Use 'A' or 'B'")
                        return
      }
    println("new player created: " + player)
    } else {
      System.err.println("wrong number of arguments! use command: player [a|b] <name>")
    }
  }


  @Override
  def update() :Unit = {
    //ToDO: implement method
    System.err.println("TUI was notified")
  }
}
